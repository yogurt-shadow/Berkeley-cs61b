import org.junit.Test;
import static org.junit.Assert.*;

public class RabinKarpAlgorithmTests {
    @Test
    public void basic() {
        String input = "hello";
        String pattern = "ell";
        assertEquals(1, RabinKarpAlgorithm.rabinKarp(input, pattern));
    }

    @Test
    public void second(){
    	String input = "dqw";
    	String pattern  = "1d2d2";
    	assertEquals(-1, RabinKarpAlgorithm.rabinKarp(input, pattern));
    }

     @Test
    public void third(){
    	String input = "dqw1d22d2d";
    	String pattern  = "1d2d2";
    	assertEquals(-1, RabinKarpAlgorithm.rabinKarp(input, pattern));
    }
}
