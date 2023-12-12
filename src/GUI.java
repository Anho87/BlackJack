import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    JButton newCard = new JButton("New Card");
    JButton noMoreCards = new JButton("No more cards");
    JButton shuffle = new JButton("Shuffle Deck");
    JButton newGame = new JButton("New game");
    ImageIcon cardBack = new ImageIcon("Cards/Background/cardBack_blue2.png");
    JLabel deckLabel = new JLabel(cardBack);
    JLabel numberOfCardsLeftInDeck = new JLabel();
    JPanel deckPanel = new JPanel(new GridLayout(2,1));
    JPanel buttonPanel = new JPanel();
    JPanel playerHandPanel = new JPanel();
    JPanel NPCHandPanel = new JPanel();
    JPanel scorePanel = new JPanel(new GridLayout(2,1));
    JLabel playerScoreLabel = new JLabel();
    JLabel NPCScoreLabel = new JLabel();
    public GUI(){
        setLayout(new BorderLayout());
        buttonPanel.add(newCard);
        buttonPanel.add(noMoreCards);
        buttonPanel.add(shuffle);
        buttonPanel.add(newGame);
        deckPanel.add(deckLabel);
        deckPanel.add(numberOfCardsLeftInDeck);
        scorePanel.add(playerScoreLabel);
        scorePanel.add(NPCScoreLabel);
        add(BorderLayout.NORTH, buttonPanel);
        add(BorderLayout.CENTER, playerHandPanel);
        add(BorderLayout.SOUTH, NPCHandPanel);
        add(BorderLayout.WEST, deckPanel);
        add(BorderLayout.EAST, scorePanel);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Black Jack");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
}
