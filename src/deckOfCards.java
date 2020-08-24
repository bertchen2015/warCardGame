import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

import static java.lang.System.exit;

public class deckOfCards {
    public static ArrayList<card> playerDeck = new ArrayList<>();
    public static ArrayList<card> playerDeck2 = new ArrayList<>();
    public static ArrayList<card> captured = new ArrayList<>();
    public static card[] deck;



    public deckOfCards() throws IOException { //combined shuffle with creating a deck.
        deck = new card[52];


        int i = 0;
        //  this for loop uses IN to loop through all the suits
        for (card.Suit s: card.Suit.values()) {
            //this dose the same but with the ranks
            for (card.Rank r: card.Rank.values()) {

                //we are now assigning card values and image to the cards. Creating the entire deck.
                deck[i] = new card(r.getRank(), s.getSuit(), r.getRankValue(), ImageIO.read(new File("cards/" + r.getFileName() + s.getFileName() + ".gif")));
                i++;
            }
        }
        //making the rand variable
        Random rand = new Random();
        card temp;
        //goes through the whole deck.
        for (int m = 0; m < deck.length; m++) {
            //set variable equal to a random number
            int num = rand.nextInt(deck.length - 1);

            temp = deck[m];
            deck[m] = deck[num];
            deck[num] = temp;
        }
        //splitting the shuffled deck in half.

        for(int j = 0; j < 26; j++){
            playerDeck.add(deck[j]);
        }
        for(int k = 26; k < 52; k++) {
            playerDeck2.add(deck[k]);
        }

    }

    //deals out the card
    public static card dealCard() {
        if (playerDeck.size() > 0) {
            //I used array's and spend literally like 12-20 hours on this project until I tried arraylists. So much better
            card tempValue = playerDeck.get(0);
            playerDeck.remove(0);
            return tempValue;
        }
        else {
            //when the players deck hits 0 you lose!
            System.out.println("You LOSE! Luck was not on your side...");
            exit(0);
            return null;

        }
    }


    public static card dealCard2(){
        if(playerDeck2.size() > 0){
            card tempValue2 = playerDeck2.get(0);
            playerDeck2.remove(0);
            return tempValue2;
        }
        else{
            //when the opponents deck hits 0 we WIN!
            System.out.println("You Win!! How easy is it?!");
            exit(0);
            return null;
        }
    }
}