package byow.Core;

import java.util.Random;

public class TestRandom {

    public static void main(String[] args) {
        Random r = new Random(123);
        for (int i = 0; i < 10; i++) {
            System.out.println(RandomUtils.uniform(r, 10));
            System.out.println(RandomUtils.uniform(r, 10, 20));
            System.out.println(RandomUtils.uniform(r));
            System.out.println(RandomUtils.uniform(r, 0.7, 2.9));

        }
    }
}
