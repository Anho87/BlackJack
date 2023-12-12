import java.util.ArrayList;

public class ImagePaths {
    private final ArrayList<String> imagePaths  = new ArrayList<>();
    public ImagePaths(){
        for (int i = 2; i < 15; i++) {
            imagePaths.add("Cards/Clubs/cardClubs" + i +".png");
        }
        for (int i = 2; i < 15; i++) {
            imagePaths.add("Cards/Diamonds/cardDiamonds" + i + ".png");
        }
        for (int i = 2; i < 15; i++) {
            imagePaths.add("Cards/Hearts/cardHearts" + i + ".png");
        }
        for (int i = 2; i < 15; i++) {
            imagePaths.add("Cards/Spades/cardSpades" + i + ".png");
        }
    }

    public String getImagePaths(int index) {
        return imagePaths.get(index);
    }
}
