/**
 * Created by hug.
 */
import java.util.Random;

public class ExperimentHelper {

    private static int logfloor(int x){
        int i = 0;
        while(Math.pow(2, i) <= x){
            i += 1;
        }
        return i - 1;
    }

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        if(N == 1){
            return 0; 
        }
        else{
            return logfloor(N) + optimalIPL(N - 1);
        }
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) /(double) N;
    }

    public static void randomInsert(BST bst){
        Random r = new Random();
        int thistime = r.nextInt(1000000);
        while(bst.contains(thistime)){
            thistime = r.nextInt(1000000);
    }
    bst.add(thistime);
    }

    public static void randomDeleteAsy(BST bst){
        if(bst == null){return;}
        Object x = bst.getRandomKey();
        bst.deleteTakingSuccessor((Comparable) x);
    }

    public static void randomDeleteSy(BST bst){
        if(bst == null){return;}
        Object x = bst.getRandomKey();
        bst.deleteTakingRandom((Comparable) x);
    }


    public static void main(String[] args){
        for(int i = 1; i < 9; i++) {
            System.out.println(optimalIPL(i));
        }
        System.out.println();
        for(int i = 1; i < 9; i++) {
            System.out.println(optimalAverageDepth(i));
        }

        BST<Integer> bst = new BST<>();
        for(int i = 0; i < 5000; i++){
            randomInsert(bst);
        }
        System.out.println(bst.size());
         for(int i = 0; i < 5000; i++){
            randomDeleteAsy(bst);
        }

        System.out.println(bst.size());

    }
}
