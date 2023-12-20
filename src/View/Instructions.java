package View;

public enum Instructions {

    PLACE_BET("Välj en summa att satsa"),
    DECIDE_NEXT_MOVE("Det är din tur. Dra ett nytt kort (Dra kort!) eller stanna (Stop!)"),
    BUSTED("Du gick över 21. Du har tyvärr förlorat. Klicka på 'Ny Omgång' för att försöka igen"),
    WON_ROUND("Grattis! Du vann. Klicka på 'Ny Omgång' för en ny runda"),
    LOST_ROUND("Tyvärr, du förlorade. Klicka på 'Ny Omgång' för att försöka igen"),
    DRAW_ROUND("Det blev oavgjort. Klicka på 'Ny Omgång' för en ny runda");

    private final String instruction;

    Instructions(String instruction) {
        this.instruction = instruction;
    }

    public String getInstruction() {
        return instruction;
    }
}

