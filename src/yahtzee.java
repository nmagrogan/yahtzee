import java.util.Scanner;
/**
 * This program runs a game of yahtzee. It plays a single player game of yahtzee.
 * CPSC 224, Spring 2020
 * Homework 3
 * No sources to cite.
 *
 * @author Nathan Magrogan
 * @version v1.8 2/28/2020
 */

public class yahtzee {

    public static void main(String[] args) {
        //read from file
        gameParameters gameParams = new gameParameters("yahtzeeConfig.txt");
        Scanner input = new Scanner(System.in);
        String inputString;

        //display old params
        gameParams.displayParameters();

        //check if user likes game params
        System.out.print("enter 'y' if you would like to change game configuration: ");
        inputString = input.next();
        inputString = inputString.toLowerCase();

        //check for a valid input
        while(!inputString.equals("y") && !inputString.equals("n")){
            System.out.print("Invalid entry, please enter y or n: ");
            inputString = input.next();
            inputString = inputString.toLowerCase();
        }



        if (inputString.charAt(0) == 'y')
            gameParams.changeParams();

        //declaration of some variables that I will be using.
        boolean stillPlaying = true;
        turn playerTurn = new turn(gameParams.getIndex(2));
        hand playerHand = new hand(gameParams.getIndex(0), gameParams.getIndex(1));
        scoreCard playerScoreCard = new scoreCard(playerHand);


        //for loop that runs as many turns as there are numbers of lines on the scorecard
        for(int turn = 0; turn < playerScoreCard.getNumLines();turn ++) {

            playerTurn.checkScoreCard(playerScoreCard);

            playerTurn.rollStep(playerHand);
            playerTurn.scoreStep(playerHand, playerScoreCard);

            playerHand.resetHand();

        }

        System.out.println("Good game here is your final scorecard");
        playerScoreCard.displayScoreCard();

        gameParams.writeFile();
    }

}
