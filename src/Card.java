import javax.swing.*;
import java.awt.*;

public class Card {
    private int type;
    private int number;
    private JLabel cardImage = new JLabel();

    //Skapar ett kort
    public int getNumber() {
        return number;
    }

    public JLabel getCardImage() {
        return cardImage;
    }

    public void setCardImage(String cardImage) {
        ImageIcon icon = new ImageIcon(cardImage);
        this.cardImage.setIcon(icon);
    }
    

    public void setNumber(int number) {
        if (number < 2 || number > 14) {
            throw new IllegalArgumentException("Måste vara högre än 2 och lägre än 14");
        } else {
            this.number = number;
        }
    }

    public void setType(int type) {
        if (type < 0 || type > 3) {
            throw new IllegalArgumentException("Måste vara mellan 0 och 3");
        } else {
            this.type = type;
        }
    }

    public String type(int type) {
        String typeString = "";
        if (type == 0) {
            typeString = "\u2663";
            return typeString;
        } else if (type == 1) {
            typeString = "\u2666";
            return typeString;
        } else if (type == 2) {
            typeString = "\u2665";
            return typeString;
        } else if (type == 3) {
            typeString = "\u2660";
            return typeString;
        }
        return typeString;
    }

    public String number(int type) {
        String typeString = "";
        if (type == 11) {
            typeString = "Knekt";
            return typeString;
        } else if (type == 12) {
            typeString = "Dam";
            return typeString;
        } else if (type == 13) {
            typeString = "Kung";
            return typeString;
        } else if (type == 14) {
            typeString = "Ess";
            return typeString;
        } else {
            typeString = "" + type;
        }
        return typeString;
    }

    @Override
    public String toString() {
        return type(type) + " " + number(number);
    }
}

