

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws FileNotFoundException {
        ArrayList<Character> guessedChars = new ArrayList<>();
        ArrayList<Character> playerGuesses = new ArrayList<>();
        ArrayList<String> words = new ArrayList<>();
        String word = "";

        System.out.println("Welcome to hangman - Press 'Enter' to start the game");
            Scanner scanner = new Scanner(new File("src/HangmanWord.csv"));
            while (scanner.hasNext()) {
                word = scanner.nextLine();
                String wordString = word;
                words.add(wordString);
        }

        int wrongGuesses = 0;

        while(true) {
            if (wrongGuesses >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + word);
                break;
            } else if(isWordGuessed(word, playerGuesses)) {
                System.out.println("You win!");
                break;
            }

            printWordState(word, playerGuesses);
            System.out.println("Guessed letters: "+guessedChars);

            System.out.println("Write a letter to see if its in the word!");
            String userGuess = getUserInput();
            guessedChars.add(userGuess.charAt(0));

            if (!isPlayerGuessCorrect(userGuess, word, playerGuesses)) {
                wrongGuesses++;
                System.out.println("\nIncorrect answer");
                printFriendlyReminder(wrongGuesses);
            }
        }
    }

    private static void printFriendlyReminder(Integer wrongGuesses) {
        if (wrongGuesses == 1) {
            System.out.println("Whoopsy that was first error");
        } else if (wrongGuesses == 2) {
            System.out.print("Another one? please try harder");
        } else if (wrongGuesses == 3) {
                System.out.println("That's third strike my friend");
        } else if (wrongGuesses == 4) {
            System.out.println("Fourth error! You only have 2 attempts left my friend");
        } else if (wrongGuesses == 5) {
            System.out.print("Last shot!");
        } else if (wrongGuesses >= 6) {
                System.out.println("Well you tried, better luck next time");
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean isPlayerGuessCorrect(String userGuess, String word, List<Character> playerGuesses) {
        playerGuesses.add(userGuess.charAt(0));
        return word.contains(userGuess);
    }

    public static void printWordState(String word, ArrayList<Character> playerGuesses){
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {
                System.out.print(word.charAt(i));
            } else {
                System.out.print("-");
            }
        }
        System.out.println();
    }

    private static boolean isWordGuessed(String word, ArrayList<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < word.length(); i++) {
            if (playerGuesses.contains(word.charAt(i))) {

                correctCount++;
            }

        }


        return (word.length() == correctCount);
    }

    public static String getUserInput(){
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}