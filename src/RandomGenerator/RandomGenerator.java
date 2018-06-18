package RandomGenerator;

import java.util.*;

public class RandomGenerator {
    // create the random object
    Random randomNumber = new Random();

    // default generator for a number till 100
    public int generateNumber() {

        // create the random number between 1 - 100
        // random gives back a number at the interval [0, seed)
        int number = randomNumber.nextInt(100) + 1;

        // return the generated number
        return number;
    }

    // generator for number that user chooses
    public int generateNumber(int x) {

        // create the random number between 1 - x
        // random gives back a number at the interval [0, seed)
        int number = randomNumber.nextInt(x) + 1;

        // return the generated number
        return number;
    }
}
