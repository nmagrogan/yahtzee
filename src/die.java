import java.util.Random;
/**
 * This program handles the dice for the game. It keeps track of the value of the die and the number of sides fo the die.
 * It rerolls the die.
 * CPSC 224, Spring 2020
 * Homework 3
 * No sources to cite.
 *
 * @author Nathan Magrogan
 * @version v1.8 2/28/2020
 */

public class die {
    private int value;
    private int numSides;

    /**
     * constructor, defaults to die being 6 sides and a value of 1
     */
    public die(){
        value = 1;
        numSides = 6;
    }

    /**
     * secondayr constructor letting you specify number of sides for a dice
     * @param sides number of sides for a die
     */
    public die(int sides){
        value = 1;
        numSides = sides;
    }

    //methods
    /**
     * gets the value of the dice
     * @return the vale of the dice
     */
    public int getValue(){
        return value;
    }

    /**
     * randomly selects value between 1 and the number of sides of the dice, and changes the value of the dice
     */
    public void rollDie(){
        Random rand = new Random();

        if(numSides==1) //if die only has one side value can only be 1
            value = 1;
        else
            value = rand.nextInt(numSides-1) + 1;

    }

    /**
     * returns possible number of sides for a die
     * @return number of sides for a die
     */
    public int getNumSides(){
        return numSides;
    }
}

