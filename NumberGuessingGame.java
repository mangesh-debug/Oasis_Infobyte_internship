import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        boolean playAgain = true;

        System.out.println("🎯 Welcome to Number Guessing Game!");

        while (playAgain) {
            int target = rand.nextInt(100) + 1; // 1..100
            int attempts = 0;
            boolean guessed = false;

            System.out.println("I'm thinking of a number between 1 and 100. Try to guess it!");

            while (!guessed) {
                System.out.print("Enter your guess: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    sc.next(); // skip invalid input
                    continue;
                }
                int guess = sc.nextInt();
                attempts++;

                if (guess < target) {
                    System.out.println("Too low! ⬇️");
                } else if (guess > target) {
                    System.out.println("Too high! ⬆️");
                } else {
                    System.out.println("Correct! 🎉 You guessed in " + attempts + " attempts.");
                    guessed = true;
                }
            }

            System.out.print("Play again? (y/n): ");
            String resp = sc.next();
            playAgain = resp.equalsIgnoreCase("y") || resp.equalsIgnoreCase("yes");
        }

        System.out.println("Thanks for playing! 👋");
        sc.close();
    }
}