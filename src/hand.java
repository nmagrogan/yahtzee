import java.util.ArrayList;
import java.util.Scanner;
/**
 * This program handles the hand. Keeps track fo the dice in the hand. It sorts the hand. Asks the user
 * which dice they want to re roll, displays the hand. It also resets the hand between turns.
 * CPSC 224, Spring 2020
 * Homework 3
 * No sources to cite.
 *
 * @author Nathan Magrogan
 * @version v1.8 2/28/2020
 */

public class hand {
    //atributes
    private ArrayList<die> myHand;
    private int handSize;


    /**
     * Creates a new hand with a default hand size of 5 dice.
     */
    public hand(){
        handSize = 5;
        myHand = new ArrayList<die>();
        for(int i = 0; i < handSize; i++) {
            myHand.add(new die());
            myHand.get(i).rollDie();
        }

    }

    /**
     * secondary constructor that lets youspecify the number of sides for a die and number of die in a hand
     * @param numSides number of sides for each die
     * @param size number of die in a hand
     */
    public hand(int numSides, int size){
        handSize = size;
        myHand = new ArrayList<die>();
        for(int i = 0; i < handSize; i++) {
            myHand.add(new die(numSides));
            myHand.get(i).rollDie();
        }

    }

    //methods
    /**
     * Prints the contents of the hand to the console
     */
    public void displayHand(){
        for(int i = 0; i < handSize; i++){
            System.out.print(myHand.get(i).getValue() + " ");
        }
        System.out.println();
    }


    /**
     *Sorts the hand from lowest value dice to highest value
     * uses the bubble sort algorithm
     */
    public void sortHand(){
        //sorts the hand lowest value in lowest index
        //bubble sort
        for (int i = 0; i < handSize-1; i++)
            for (int j = 0; j < handSize-i-1; j++)
                if (myHand.get(j).getValue() > myHand.get(j+1).getValue())
                {
                    // swap
                    die temp = myHand.get(j);
                    myHand.set(j,myHand.get(j+1));
                    myHand.set(j+1,temp);
                }
    }

    /**
     * Asks the user which dice they want to keep
     * re-rolls the dice the user doesn't want to keep.
     */
    public void reRollHand(){
        Scanner input = new Scanner(System.in);
        boolean invalid = false;

        System.out.print("Enter dice to keep (y or n): ");
        String handKeepString = input.next();
        handKeepString = handKeepString.toLowerCase();
        invalid = checkInput(handKeepString);


        while(invalid){
            System.out.print("Invalid entry, please enter y/n for the number of dice in play\neg: yynnn for 5 dice in play,\nand only use chars y or n\nEnter dice to keep (y or n): ");
            handKeepString = input.next();
            handKeepString =  handKeepString.toLowerCase();
            invalid = checkInput(handKeepString);
        }

        for(int i =0; i< handSize;i++){
            if(handKeepString.charAt(i) == 'n'){
                myHand.get(i).rollDie();
            }
        }
    }

    /**
     * gets the value of dice at a specific index, used by scoreCard to get value of dice in hand,
     * but doesn't give it the actual dice.
     * @param index index in hand of the dice you want.
     * @return value of the dice at that index
     */
    public int getIndex(int index){
        return myHand.get(index).getValue();

    }

    /**
     * gets the die at an index
     * @param index index of die wanted
     * @return die at index
     */
    public die getDie(int index){
        return myHand.get(index);
    }

    /**
     * resets all fo the dice in the hand
     */
    public void resetHand(){
        for(int i = 0; i<handSize;i++){
            myHand.get(i).rollDie();
        }
    }

    /**
     * returns the size of the hand
     * @return size of the hand
     */
    public  int getHandSize(){
        return handSize;
    }

    /**
     * returns true if the input is an invalid input, fist checks if the input is the right length,
     * then checks if the input contains only y or n
     * @param input input string to check
     * @return true if input is invalid, false if input is valid
     */
    private boolean checkInput(String input){
        char[] validChars = {'y','n'};
        if(input.length() != handSize){
            return true;
        }

        for(int i =0; i< handSize;i++){
            if(input.charAt(i) != validChars[0] && input.charAt(i)  != validChars[1]){
                return true;
            }
        }
        return false;

    }


}

