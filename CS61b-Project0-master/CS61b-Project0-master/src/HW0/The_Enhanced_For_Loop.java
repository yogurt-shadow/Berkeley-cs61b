package HW0;

/**
 * Java also supports iteration through an array using an “enhanced for loop”. The
 * basic idea is that there are many circumstances where we don’t actually care about
 * the index at all. In this case, we avoid creating an index variable using a special
 * syntax involving a colon.
 * <p>
 * For example, in the code below, we do the exact thing as in BreakDemo above.
 * However, in this case, we do not create an index i. Instead, the String s takes
 * on the identity of each String in a exactly once, starting from a[0], all the way
 * up to a[a.length - 1]. You can try out this code at this link.
 * https://goo.gl/wmhVPM
 */
public class The_Enhanced_For_Loop {

    public static void main(String[] args) {
        String[] a = {"cat", "dog", "laser horse", "ketchup", "horse", "horbse"};

        for (String s : a) {
            for (int j = 0; j < 3; j += 1) {
                System.out.println(s);
                if (s.contains("horse")) {
                    break;
                }
            }
        }
    }

}
