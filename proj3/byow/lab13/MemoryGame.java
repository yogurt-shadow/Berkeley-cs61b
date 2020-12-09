package byow.lab13;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;

public class MemoryGame {
    private Random r;
    private int width;
    private int height;
    private int round;
    private Random rand;
    private boolean gameOver;
    private boolean playerTurn;
    private static final char[] CHARACTERS = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final String[] ENCOURAGEMENT = {"You can do this!", "I believe in you!",
                                                   "You got this!", "You're a star!", "Go Bears!",
                                                   "Too easy for you!", "Wow, so impressive!"};

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Please enter a seed");
            return;
        }

        int seed = Integer.parseInt(args[0]);
        Random r = new Random(seed);
        MemoryGame game = new MemoryGame(40, 40, r);
        game.startGame();
    }

    public MemoryGame(int width, int height, Random r) {
        /* Sets up StdDraw so that it has a width by height grid of 16 by 16 squares as its canvas
         * Also sets up the scale so the top left is (0,0) and the bottom right is (width, height)
         */
        this.r = r;
        this.width = width;
        this.height = height;
        StdDraw.setCanvasSize(this.width * 16, this.height * 16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, this.width);
        StdDraw.setYscale(0, this.height);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        //TODO: Initialize random number generator
    }

    public String generateRandomString(int n) {
        //TODO: Generate random string of letters of length n
        String result = "";
        for(int i = 0; i < n; i++){
            int number = r.nextInt(26);
            result += String.valueOf(CHARACTERS[number]);
        }

        return result;
    }

    public void drawFrame(String s, String current) {
        //TODO: Take the string and display it in the center of the screen
        //TODO: If game is not over, display relevant game information at the top of the screen
        StdDraw.clear();

        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 30));
        StdDraw.text(width / 2, height / 2, s);

        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 25));
        StdDraw.text(4, height - 1, "Round: " + round );
        StdDraw.text(width / 2, height - 1, current);
        String encourage = ENCOURAGEMENT[r.nextInt(7)];
        StdDraw.text(width - encourage.length() / 2 , height - 1, encourage);

        StdDraw.line(0, height - 2, width, height - 2);
        StdDraw.show();

    }

    public void flashSequence(String letters) {
        //TODO: Display each character in letters, making sure to blank the screen between letters
        for(int i = 0; i < letters.length(); i++){
            drawFrame(letters.substring(i, i + 1), "Watch!");
            StdDraw.pause(1000);

            StdDraw.clear();
            drawFrame("", "Watch!");
            StdDraw.show();
            StdDraw.pause(500);

        }
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        int charnumber = 0;
        String result = "";
        while(charnumber < n) {
            while (StdDraw.hasNextKeyTyped()) {
                result += StdDraw.nextKeyTyped();
                drawFrame(result, "Type!");
                charnumber += 1;
            }
        }
        return result;
    }

    public void startGame() {
        //TODO: Set any relevant variables before the game starts
        //TODO: Establish Engine loop
       this.round = 1;
       while(true){
           drawFrame("Round: " + round, "Watch");
           String current = generateRandomString(round);
           flashSequence(current);

           drawFrame("", "Type!");
           String user = solicitNCharsInput(round);
           StdDraw.pause(1000);
           if(!user.equals(current)){
               drawFrame("Game Over! You made it to round: " + round , "Game over!");
               break;
           }
           else{
               this.round += 1;
           }
       }
    }

}
