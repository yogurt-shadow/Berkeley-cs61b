import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testisPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("c"));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("AbabaababA"));
        assertFalse(palindrome.isPalindrome("ffffFFFF"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("raceCaR"));
    }
    static CharacterComparator cc = new OffByOne();
    @Test
    public void testisPalindrome2(){
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("%lak&", cc));
        assertTrue(palindrome.isPalindrome("a", cc));
        assertFalse(palindrome.isPalindrome("ak", cc));
        assertTrue(palindrome.isPalindrome("tufts", cc));
    }
}    