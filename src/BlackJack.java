import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class BlackJack implements ActionListener {
    
    DeckOfCards deckOfCards = new DeckOfCards();
    GUI gui = new GUI();
    
    int numberOfCardsInDeck = 51;
    Random random = new Random();
    int playerScore = 0;
    int npcScore = 0;
    
    public BlackJack(){
        gui.newCard.addActionListener(this);
        gui.noMoreCards.addActionListener(this);
        gui.shuffle.addActionListener(this);
        gui.newGame.addActionListener(this);
        
        playerPlay();
        playerPlay();
        
    }
    public void playerPlay(){
        Card tempCard;
        tempCard = deckOfCards.getDeckOfCards().get(randomCard());
        gui.playerHandPanel.add(tempCard.getCardImage());
        playerScore += checkCardValue(tempCard);
        gui.playerScoreLabel.setText(String.valueOf(playerScore));
        guiRepaint();
    }
    public void npcPlay(){
        while (npcScore < playerScore && npcScore < 21) {
            Card tempCard;
            tempCard = deckOfCards.getDeckOfCards().get(randomCard());
            gui.NPCHandPanel.add(tempCard.getCardImage());
            npcScore += checkCardValue(tempCard);
            gui.NPCScoreLabel.setText(String.valueOf(npcScore));
            guiRepaint();
        }
    }
    
    public int checkCardValue(Card card){
        int cardValue = card.getNumber();
        if (cardValue > 10 && cardValue < 14){
            cardValue = 10;
        }else if (cardValue == 14){
            cardValue = 11;
        }
        return cardValue;
    }

    public int randomCard() {//returnerar ett random tal mellan 0
        int randomCard = random.nextInt(0, numberOfCardsInDeck);
        removeCardFromDeck(deckOfCards.getDeckOfCards().get(randomCard));
        return randomCard;
    }
    public void removeCardFromDeck (Card card){//Tar bort kortet som dras ur leken
        deckOfCards.getDeckOfCards().remove(card);
        deckOfCards.getDeckOfCards().trimToSize();
        numberOfCardsInDeck --;
        updateDeckSize();
    }
    public void updateDeckSize(){
        gui.numberOfCardsLeftInDeck.setText(String.valueOf(deckOfCards.getDeckOfCards().size()));
        guiRepaint();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gui.shuffle){
            deckOfCards = new DeckOfCards();
            numberOfCardsInDeck = 51;
            guiRepaint();
        } else if (e.getSource() == gui.newCard) {
            Card tempCard;
            tempCard = deckOfCards.getDeckOfCards().get(randomCard());
            gui.playerHandPanel.add(tempCard.getCardImage());
            playerScore += checkCardValue(tempCard);
            gui.playerScoreLabel.setText(String.valueOf(playerScore));
            guiRepaint();
        } else if (e.getSource() == gui.noMoreCards) {
            npcPlay();
        } else if (e.getSource() == gui.newGame) {
            gui.playerHandPanel.removeAll();
            gui.NPCHandPanel.removeAll();
            gui.playerScoreLabel.setText("0");
            gui.NPCScoreLabel.setText("0");
            playerScore = 0;
            npcScore = 0;
            playerPlay();
            playerPlay();
        }
    }
    
    public void guiRepaint(){
        gui.revalidate();
        gui.repaint();
    }
    
    public static void main(String[] args) {
        new BlackJack();
    }
}
