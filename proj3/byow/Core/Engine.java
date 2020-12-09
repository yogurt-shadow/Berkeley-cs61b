package byow.Core;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {

        StdDraw.setCanvasSize(WIDTH * 16, this.HEIGHT * 16);
        Font font = new Font("Times New Roman", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();

        StdDraw.setPenColor(Color.BLUE);
        StdDraw.text(WIDTH / 2, HEIGHT / 5 * 4, "CS61B: The Game");
        StdDraw.text(WIDTH / 2, HEIGHT / 2 + 2, "New Game (N/n)");
        StdDraw.text(WIDTH / 2, HEIGHT / 2, "Load Game (L/l)");
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 2, "Quit Game (Q/q)");
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.text(WIDTH / 2,  7, "wzh@author");
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.show();

        StdDraw.pause(2000);

        solicitNCharsInput_choice();


    }

    private void solicitNCharsInput_choice(){
        while (StdDraw.hasNextKeyTyped()) {
            char current = StdDraw.nextKeyTyped();
           switch (current){
               case 'n': case 'N':
                   drawSeed();
                   String user = solicitNCharsInput(100);
                   interactWithInputString('n' + user);
               return;
               case 'q': case 'Q': good_bye();
               return;
               default:
                   return;
           }
        }
    }

    private void good_bye(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 50));
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 2, "Good Bye !");
        StdDraw.show();
    }


    private void drawSeed(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.text(WIDTH / 2, HEIGHT / 2 + 2,"SEED");
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledRectangle(WIDTH / 2 , HEIGHT / 2 - 2, 8, 2);
        StdDraw.show();
    }

    public String solicitNCharsInput(int n) {
        //TODO: Read n letters of player input
        int charnumber = 0;
        String result = "";
        while(charnumber < n) {
            while (StdDraw.hasNextKeyTyped()) {
                char current = StdDraw.nextKeyTyped();
                result += current;
                drawFrame(result);
                charnumber += 1;
                if(current == 's' || current == 'S'){
                    return result;
                }
            }
        }
        return result;
    }

    public void drawFrame(String s) {
        StdDraw.clear(Color.BLACK);
        drawSeed();
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 30));
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 2, s);
        StdDraw.show();
    }




    /**
     * Method used for autograding and testing your code. The input string will be a series
     * of characters (for example, "n123sswwdasdassadwas", "n123sss:q", "lwww". The engine should
     * behave exactly as if the user typed these characters into the engine using
     * interactWithKeyboard.
     *
     * Recall that strings ending in ":q" should cause the game to quite save. For example,
     * if we do interactWithInputString("n123sss:q"), we expect the game to run the first
     * 7 commands (n123sss) and then quit and save. If we then do
     * interactWithInputString("l"), we should be back in the exact same state.
     *
     * In other words, both of these calls:
     *   - interactWithInputString("n123sss:q")
     *   - interactWithInputString("lww")
     *
     * should yield the exact same world state as:
     *   - interactWithInputString("n123sssww")
     *
     * @param input the input string to feed to your program
     * @return the 2D TETile[][] representing the state of the world
     */
    public TETile[][] interactWithInputString(String input) {
        // TODO: Fill out this method so that it run the engine using the input
        // passed in as an argument, and return a 2D tile representation of the
        // world that would have been drawn if the same inputs had been given
        // to interactWithKeyboard().
        //
        // See proj3.byow.InputDemo for a demo of how you can make a nice clean interface
        // that works for many different input types.

        String str = "";
        boolean seed_check = false;
        boolean seed_completed = false;
        for(int i = 0; i < input.length(); i++){
            if(input.charAt(i) == 's' || input.charAt(i) == 'S'){
                seed_check = false;
                seed_completed = true;
            }
            if(seed_check && !seed_completed){
                str += input.charAt(i);
            }
            if(input.charAt(i) == 'n' || input.charAt(i) == 'N'){
                seed_check = true;
            }
        }
        long seed = Long.parseLong(str);

        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT, 0, 0);

        TETile[][] world = MapGenerator.map_generator(seed);
        ter.renderFrame(world);


        return world;
    }
}
