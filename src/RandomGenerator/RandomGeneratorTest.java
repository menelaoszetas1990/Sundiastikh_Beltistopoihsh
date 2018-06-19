package RandomGenerator;

// class for test reasons
public class RandomGeneratorTest {
    public static void main (String args[]) {
        RandomGenerator testNumber = new RandomGenerator();
        for (int i = 0; i < 50; i++) {
            int number = testNumber.generateNumber();

            // console logging for test reasons
            System.out.println("Random number is: " + number);


            number = testNumber.generateNumber(5);

            // console logging for test reasons
            System.out.println("Random number til 5 is: " + number);

        }
    }
}
