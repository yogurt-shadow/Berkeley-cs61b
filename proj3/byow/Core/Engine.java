package byow.Core;

import byow.SaveDemo.Editor;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import edu.princeton.cs.introcs.StdDraw;

import java.awt.*;
import java.io.*;
import java.util.Map;

public class Engine {
    TERenderer ter = new TERenderer();
    /* Feel free to change the width and height. */
    public static final int WIDTH = 80;
    public static final int HEIGHT = 30;

    private int avator_x;
    private int avator_y;

    private String inputsource;

    /**
     * Method used for exploring a fresh world. This method should handle all inputs,
     * including inputs from the main menu.
     */
    public void interactWithKeyboard() {
        inputsource = "keyboard";

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
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 20));
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.text(WIDTH / 2, 5, "version 3.0");
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.text(WIDTH / 2, 3, "Date: Dec. 9, 2020");
        StdDraw.show();

        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 30));

        StdDraw.pause(1500);

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

               case 'L': case 'l':
                   ter.initialize(WIDTH, HEIGHT);
                   Saveworld save = loadworld();
                   TETile[][] world = save.world();
                   display(ter, world);
                   control(world);
                   break;

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
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 30));
        StdDraw.text(WIDTH / 2,  7, "wzh@author");
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

    private void saveworld(Saveworld saveworld) {
        File f = new File("./proj3_save_data");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(saveworld);
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }

    private  Saveworld loadworld() {
        File f = new File("./proj3_save_data");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (Saveworld) os.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no Editor has been saved yet, we return a new one. */
        return new Saveworld(interactWithInputString("n1232s"));
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


        ter.initialize(WIDTH, HEIGHT, 0, 0);

        MapGenerator mg = new MapGenerator();

        TETile[][] world = mg.map_generator(seed);
       avator_x = mg.avator_x();
        avator_y = mg.avator_y();

       display(ter, world);


        if(inputsource == "keyboard"){
            control(world);
        }


        return world;
    }

    private void display(TERenderer ter, TETile[][] world){
        ter.renderFrame(world);
    }

    private void control(TETile[][] world){
        boolean wait = false;
        StdDraw.clear();
        display(ter, world);
        StdDraw.pause(1000);
        while(StdDraw.hasNextKeyTyped()){
            char current = StdDraw.nextKeyTyped();
            switch(current){
                case 'a': case 'A':
                    wait = false;
                    if(world[avator_x - 1][avator_y].equals(Tileset.WALL)){
                    break;
                }
                    if(world[avator_x - 1][avator_y].equals(Tileset.FLOOR)){
                        world[avator_x - 1][avator_y] = Tileset.AVATAR;
                        world[avator_x][avator_y] = Tileset.FLOOR;
                        avator_x -= 1;
                        display(ter, world);
                        StdDraw.pause(800);
                        break;
                    }
                    if(world[avator_x - 1][avator_y].equals(Tileset.LOCKED_DOOR)){
                        win();
                        break;
                    }

                case 'w': case 'W':
                    wait = false;
                    if(world[avator_x][avator_y + 1].equals(Tileset.WALL)){
                        break;
                    }
                    if(world[avator_x][avator_y + 1].equals(Tileset.FLOOR)){
                        world[avator_x][avator_y + 1] = Tileset.AVATAR;
                        world[avator_x][avator_y] = Tileset.FLOOR;
                        avator_y += 1;
                        display(ter, world);
                        StdDraw.pause(800);
                        break;
                    }
                    if(world[avator_x][avator_y + 1].equals(Tileset.LOCKED_DOOR)){
                        win();
                        break;
                    }
                case 's': case 'S':
                    wait = false;
                    if(world[avator_x][avator_y - 1].equals(Tileset.WALL)){
                        break;
                    }
                    if(world[avator_x][avator_y - 1].equals(Tileset.FLOOR)){
                        world[avator_x][avator_y - 1] = Tileset.AVATAR;
                        world[avator_x][avator_y] = Tileset.FLOOR;
                        avator_y -= 1;
                        display(ter, world);
                        StdDraw.pause(800);
                        break;
                    }
                    if(world[avator_x][avator_y - 1].equals(Tileset.LOCKED_DOOR)){
                        win();
                        break;
                    }
                case 'd': case 'D':
                    wait = false;
                    if(world[avator_x + 1][avator_y].equals(Tileset.WALL)){
                        break;
                    }
                    if(world[avator_x + 1][avator_y].equals(Tileset.FLOOR)){
                        world[avator_x + 1][avator_y] = Tileset.AVATAR;
                        world[avator_x][avator_y] = Tileset.FLOOR;
                        avator_x += 1;
                        display(ter, world);
                        StdDraw.pause(800);
                        break;
                    }
                    if(world[avator_x + 1][avator_y].equals(Tileset.LOCKED_DOOR)){
                        win();
                        break;
                    }
                case ':': wait = true; break;
                case 'q':
                    saveworld(new Saveworld(world));
                    break;

                default:
                    return;
            }
        }
    }

    private void win(){
        StdDraw.clear(Color.BLACK);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 50));
        StdDraw.text(WIDTH / 2, HEIGHT / 2 - 2, "YOU WIN !");
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.setFont(new Font("Times New Roman", Font.BOLD, 30));
        StdDraw.text(WIDTH / 2,  7, "wzh@author");
        StdDraw.show();
    }
}
