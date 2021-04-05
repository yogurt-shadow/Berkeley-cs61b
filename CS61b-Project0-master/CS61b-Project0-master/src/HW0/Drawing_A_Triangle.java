package HW0;

/**
 * print triangle
 * <p>
 * result:
 * *
 * **
 * ***
 * ****
 * *****
 * ******
 * *******
 * ********
 * *********
 * **********
 */
public class Drawing_A_Triangle {
    public static void main(String[] args) {
        int linemax = 10;
        char star = '*';
        for (int i = 1; i <= linemax; i++) {
            for (int i1 = 1; i1 <= i; i1++) {
                System.out.print(star);
                if (i1 == i) {
                    System.out.println();
                }

            }
        }
    }
}
