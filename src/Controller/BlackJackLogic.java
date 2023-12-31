package Controller;

import Model.*;
import Model.CardFactory.Card;
import View.GUI;
import View.Instructions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BlackJackLogic implements ActionListener {

    private DeckOfCards deckOfCards;
    private GUI gui;
    private final User user;
    private final House house;
    private String userName;
    private int currentBet;
    private int currentCapital;


    public BlackJackLogic() {
        setUserValues();

        deckOfCards = new DeckOfCards();
        user = new User(userName, currentCapital);
        house = new House();

        gui = new GUI();
        gui.newGame.addActionListener(this);
        gui.noMoreCards.addActionListener(this);
        gui.newCard.addActionListener(this);
        gui.rules.addActionListener(this);
        gui.exit.addActionListener(this);
        gui.setTotalCapital(currentCapital);
        gui.setPlayerName(userName);
        nextRound();

    }

    public void discardAllHands() {
        user.discardHand();
        house.discardHand();
    }

    public void updateAllHandImages() {
        gui.updateUserHandImages(getCardImages(getUser()));
        gui.updateHouseHandImages(getCardImages(getHouse()));
    }


    @Override
    public void actionPerformed(ActionEvent e) {


        if (e.getSource() == gui.newCard) {
            userDrawCard();
            gui.updateUserHandImages(getCardImages(getUser()));
            gui.updateInstructions(Instructions.DECIDE_NEXT_MOVE.getInstruction() +
                    ". Din hand ger " + user.getHandValue() + " poäng och" +
                    " du har "  + chanceToGoBust() + "% chans att förlora om du drar ett kort till!");
            if (user.getHandValue() == -1) {
                gui.updateInstructions(Instructions.BUSTED.getInstruction());
                JOptionPane.showMessageDialog(null, "You're bust!");
                gui.newCard.setEnabled(false);
                gui.noMoreCards.setEnabled(false);


            }

        } else if (e.getSource() == gui.noMoreCards) {
            gui.removeUpsideDownCard();
            gui.newCard.setEnabled(false);
            gui.noMoreCards.setEnabled(false);
            while (getHouse().getHandValue() < 17 && getHouse().getHandValue() > 0) {
                houseDrawCard();
                gui.updateHouseHandImages(getCardImages(getHouse()));

            }

            switch (calculateWinner()) {
                case WIN ->
                        JOptionPane.showMessageDialog(null, EndOfRound.WIN.getEndOfRound() + payOutWinnings() + "€");
                case LOSE -> JOptionPane.showMessageDialog(null, EndOfRound.LOSE.getEndOfRound());
                case DRAW -> JOptionPane.showMessageDialog(null, EndOfRound.DRAW.getEndOfRound());
            }

        } else if (e.getSource() == gui.newGame) {
            nextRound();

        } else if (e.getSource() == gui.rules) {
            gui.showRules();

        } else if (e.getSource() == gui.exit) {
            System.exit(0);
        }


    }

    private void nextRound() {
        if (deckOfCards.getDeckOfCards().size() < 15) {
            deckOfCards.createCardsFromFactory();
        }
        gui.updateInstructions(Instructions.PLACE_BET.getInstruction());
        gui.newRoundLayout();
        gui.newCard.setEnabled(true);
        gui.noMoreCards.setEnabled(true);
        discardAllHands();
        updateAllHandImages();
        placeBet();
        dealCardsAtStartOfRound();
        gui.updateInstructions(Instructions.DECIDE_NEXT_MOVE.getInstruction() +
                ". Din hand ger " + user.getHandValue() + " poäng och" +
                " du har " + chanceToGoBust() + "% chans att förlora om du drar ett kort till!");
        updateAllHandImages();
    }

    private void placeBet() {
        currentBet = 0;
        String answer = JOptionPane.showInputDialog(null, "Välj insats");
        int bet;
        try{
            bet = Integer.parseInt(answer);
        }catch (NumberFormatException e){
            bet = 1;
        }
        

        if (bet < currentCapital) {
            currentBet = bet;
            user.subractBetFromCapital(bet);
            gui.setTotalCapital(user.getCurrentCapital());
        }
        
        gui.setCurrentBet(currentBet);
        gui.updateInstructions(Instructions.DECIDE_NEXT_MOVE.getInstruction());
    }
    
    public List<JLabel> getCardImages(Player player) {
        List<JLabel> cardImages = new ArrayList<>();
        for (Card card : player.getCurrentHand()) {
            cardImages.add(card.getImage());
        }

        return cardImages;
    }


    public void setUserValues() {
        userName = JOptionPane.showInputDialog("Skriv in ditt namn: ");
        String capital = JOptionPane.showInputDialog("Skriv in ditt kapital: ");
        currentBet = 0;
        try {
            currentCapital = Integer.parseInt(capital);
        } catch (NumberFormatException e) {
            currentCapital = 1000;
        }
    }

    public EndOfRound calculateWinner() {

        if (user.getHandValue() > house.getHandValue()) {
            gui.updateInstructions(Instructions.WON_ROUND.getInstruction());
            return EndOfRound.WIN;

        } else if (user.getHandValue() < house.getHandValue()) {
            gui.updateInstructions(Instructions.LOST_ROUND.getInstruction());
            return EndOfRound.LOSE;

        } else {
            gui.updateInstructions(Instructions.DRAW_ROUND.getInstruction());
            return EndOfRound.DRAW;
        }
    }

    public void houseDrawCard() {
        house.drawCard(deckOfCards.dealCard());
    }

    public void userDrawCard() {
        user.drawCard(deckOfCards.dealCard());
    }

    public void dealCardsAtStartOfRound() {
        user.drawCard(deckOfCards.dealCard());
        house.drawCard(deckOfCards.dealCard());
        user.drawCard(deckOfCards.dealCard());

    }

    public int payOutWinnings() {
        int winnings = currentBet * 2;
        user.addToTotalCapital(winnings);
        return winnings;
    }

    public int chanceToGoBust() {
        int userHandValue = 21 - user.getHandValue();
        int cardsLeftThatDoesntMakeUserGoBust = 0;
        for (Card card : deckOfCards.getDeckOfCards()) {
            if (card.getValue() <= userHandValue || (card.getValue() == 14 && userHandValue != 0)) {
                cardsLeftThatDoesntMakeUserGoBust++;
            } else if (card.getValue() > 10 && card.getValue() < 14 && userHandValue >= 10) {
                cardsLeftThatDoesntMakeUserGoBust++;
            }
        }
        double chanceToNoTGoBust = ((double)cardsLeftThatDoesntMakeUserGoBust / deckOfCards.getDeckOfCards().size()) * 100;
        int roundedChanceToGoBust = (int) Math.floor(100 - chanceToNoTGoBust);
        return roundedChanceToGoBust;
    }

    public List<Card> getUserCards() {
        return user.getCurrentHand();
    }

    public User getUser() {
        return user;
    }

    public House getHouse() {
        return house;
    }

    public static void main(String[] args) {
        new BlackJackLogic();
    }
}
