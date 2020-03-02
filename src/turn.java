import java.util.Scanner;

/**
 * This program handles the turns for the game. It keeps track of how many times to reroll.
 * Keeps track of when other classes should display the hand, reroll the hand, sort the hand,
 * score the hand, display the possible scores.
 * CPSC 224, Spring 2020
 * Homework 3
 * No sources to cite.
 *
 * @author Nathan Magrogan
 * @version v1.8 2/28/2020
 */

public class turn {
    //atributes
    private int numReRolls;

    /**
     * constructor, defaults to die being 6 sides and a value of 1
     */
    public turn(){
        numReRolls = 2;
    }

    /**
     * secondary constructor that lets you specify the number of rolls per turn
     * @param rolls number of rolls per turn.
     */
    public turn(int rolls){
        numReRolls = rolls - 1;
    }

    //methods

    /**
     * gets the value of the dice
     * @return the vale of the dice
     */
    public int getReRolls(){
        return numReRolls;
    }

    /**
     * takes care of the roll step of the turn, prints out the initial roll, and calls reroll the
     * specified number of times.
     * @param myHand the current hand for the turn
     */
    public void rollStep(hand myHand){


        System.out.print("Your roll was: ");
        myHand.displayHand();

        for(int i = 0;i< numReRolls;i++){
            myHand.reRollHand();
            System.out.print("Your roll was: ");
            myHand.displayHand();
        }
    }

    /**
     * tracks the score step of the game, prints the sorted hand, calls score hand method
     * then displays all of the possible scores
     * @param myHand the current hand for the turn
     * @param myScoreCard the current scorecard
     */
    public void scoreStep(hand myHand, scoreCard myScoreCard){
        System.out.print("Here is your sorted hand: ");
        myHand.sortHand();
        myHand.displayHand();
        myScoreCard.scoreHand(myHand);
        myScoreCard.displayPossibleScores();
        myScoreCard.chooseLineToScore();

    }


    /**
     * checks to see if the user wants to see the scorecard.
     * @param myScoreCard users scorecard
     */
    public void checkScoreCard(scoreCard myScoreCard){
        Scanner input = new Scanner(System.in);
        String inputString;

        System.out.print("Would you like to see the score card (y/n): ");
        inputString = input.next();
        inputString = inputString.toLowerCase();

        //check for a valid input
        while(inputString.charAt(0) != 'y' && inputString.charAt(0) !='n'){
            System.out.print("Invalid entry, please enter y or n: ");
            inputString = input.next();
            inputString = inputString.toLowerCase();
        }

        if(inputString.equals("y"))
            myScoreCard.displayScoreCard();

    }


}
