import java.io.IOException;

public class cardGame {
    public static deckOfCards deck;
    public static deckOfCards playerDeck;
    public static deckOfCards playerDeck2;

    public static void main(String[] args) throws IOException {
        deck = new deckOfCards();
        new cardGUI();
    }
}