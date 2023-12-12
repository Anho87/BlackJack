import java.util.ArrayList;

public class DeckOfCards {
    private final ArrayList<Card> deckOfCards = new ArrayList<>();
    //Skapar en kortlek med 52 unika kort
    public DeckOfCards(){
        for (int i = 0; i < 4; i++) {
            for (int j = 2; j < 15; j++) {
                Card card = new Card();
                card.setType(i);
                card.setNumber(j);
                deckOfCards.add(card);
            }
        }
        setCardImages();
    }

    private void setCardImages() {
        ImagePaths imagePaths = new ImagePaths();
        for (int i = 0; i < 52; i++) {
            deckOfCards.get(i).setCardImage(imagePaths.getImagePaths(i));
        }
    }

    
    public ArrayList<Card> getDeckOfCards() {
        return deckOfCards;
    }

    @Override
    public String toString() {

        return "Deck of Cards: " + deckOfCards;
    }
}
