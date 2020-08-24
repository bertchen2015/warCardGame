import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.*;

public class cardGUI implements ActionListener {
    private JFrame window;
    private JButton guessButton;
    private card currentCard;
    private card currentCard2;
    private ImageIcon cardImg;
    private ImageIcon cardImg2;
    private JTextArea gameInfo;
    private JLabel imgHolder;
    private JLabel imgHolder2;
    private ImageIcon cardBack;
    private ImageIcon cardBack2;
    private JPanel gameContainer;




    public int total = 0;
    public int total2 = 0;


    public cardGUI() throws IOException {

        //Creating the frame
        window = new JFrame("Card Game: WAR");

        //learned how to edit the screensize so it looks better!

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int height = screenSize.height / 2;
        int width = screenSize.width / 2;
        window.setPreferredSize(new Dimension(width, height));


        // creating the container
        gameContainer = new JPanel(new GridLayout(3, 3, 10, 10));

        // displaying info for player
        gameInfo = new JTextArea(5, 40);

        gameInfo = new JTextArea("Play the card game War! Each player draws a card, the larger card gets captured by " +
                "the winning player and put to the bottom of their deck! If the ranks tie then another card will be " +
                "drawn! Winner takes all! Don't run out of cards or you'll lose! ");
        gameInfo.setLineWrap(true);
        gameInfo.setWrapStyleWord(true);
        gameInfo.setEditable(false);


        //for the card images
        cardBack = new ImageIcon(ImageIO.read(new File("cards/b.gif")));
        cardImg = new ImageIcon();
        imgHolder = new JLabel(cardImg);

        cardBack2 = new ImageIcon(ImageIO.read(new File("cards/b.gif")));
        cardImg2 = new ImageIcon();
        imgHolder2 = new JLabel(cardImg2);


        // guess button
        guessButton = new JButton("FIGHT!");
        guessButton.addActionListener(this);

        // For closing the game
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.add(gameContainer);

        //Vamping the game container
        gameContainer.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        //gameContainer.add(new JLabel(cardBack));
        gameContainer.add(imgHolder);
        gameContainer.add(imgHolder2);
        //gameContainer.add(new JLabel(cardBack2));
        gameContainer.add(gameInfo);
        gameContainer.add(guessButton);


        //Making the window fit
        window.pack();
        window.setVisible(true);


    }
    //made a function instead of just doing an onclick because I had to recurse  through it if the ranks tied.

    public void warFunction() {
        //sets these temp cards to those card values
        currentCard = cardGame.playerDeck.dealCard();
        currentCard2 = cardGame.playerDeck2.dealCard2();

        //sets totals as the rank numeric value so we can compare it
        total = currentCard.getRankValue();
        total2 = currentCard2.getRankValue();


        //temporarily adds them to another arrayList in case of ties so we can easily deposit all of them into a deck.
        deckOfCards.captured.add(currentCard);
        deckOfCards.captured.add(currentCard2);

        //conditions
        if (total > total2) {
            //if player 1 won, it will dump all cards in the temp arraylist. while loop because it might be more than just the 2 if there are ties
            while (!deckOfCards.captured.isEmpty()) {
                deckOfCards.playerDeck.add(deckOfCards.captured.remove(0));
                //resetting the totals
                total = 0;
                total2 = 0;
                //updating the texts so user knows whos winning
                gameInfo.setText("THE BATTLE RAGES ON!\nCards Remaining:\nP1: " + deckOfCards.playerDeck.size() + "\nP2: " + deckOfCards.playerDeck2.size());
            }

        } else if (total2 > total) {
            deckOfCards.playerDeck2.add(deckOfCards.captured.remove(0));
            total = 0;
            total2 = 0;
            gameInfo.setText("THE BATTLE RAGES ON!\nCards Remaining:\nP1: " + deckOfCards.playerDeck.size() + "\nP2: " + deckOfCards.playerDeck2.size());

        } else if (total == total2) {//recurse if tie, notice the temp arraylist isn't emptied!
            warFunction();

        }

        //making the image line up
        if(!deckOfCards.playerDeck.isEmpty()){
            cardImg.setImage((currentCard.getCardImage()));
            cardImg2.setImage(currentCard2.getCardImage());
        }


        //was told that this refreshes the page
        imgHolder.repaint();
        imgHolder2.repaint();
    }


    //the action event!
    @Override
    //on click! Basically
    public void actionPerformed(ActionEvent e) {
        warFunction();

    }
}