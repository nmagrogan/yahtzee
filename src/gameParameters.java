import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This program handles the game parameters for the game. It reads the parameters from a file, writes
 * them to the file at the end of a run. Can print the parameters for a user, and lets the user change
 * the parameters of the game.
 * CPSC 224, Spring 2020
 * Homework 3
 * No sources to cite.
 *
 * @author Nathan Magrogan
 * @version v1.8 2/28/2020
 */
public class gameParameters {
    //atributes
    private ArrayList<Integer> gameParams = new ArrayList<Integer>();


    /**
     * constructor, that opens a file and and stores the game parameters
     * @param pathname pathname of file containing game parameters
     */
    public gameParameters(String pathname ){
        Scanner inFile = null;
        try {
            inFile = new Scanner(new File(pathname));
            while (inFile.hasNextLine()){
                int line = Integer.parseInt(inFile.nextLine());
                gameParams.add(line);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }

    }

    /**
     * writes game parameters to a file, writes to file" yahtzeeConfig.txt
     */
    public void writeFile(){
        try{
            PrintStream outFile = new PrintStream(new File("yahtzeeConfig.txt"));
            for(int i = 0; i < gameParams.size(); i++){
                outFile.println(gameParams.get(i));
            }
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");

        }
    }

    /**
     * changes the game parameters
     * steps through each parameter asking user what they want to change the parameter to.
     */
    public void changeParams(){
        Scanner input = new Scanner(System.in);
        String inputString;
        boolean invalid;

        System.out.print("Enter number of sides on each die: ");
        inputString = input.next();
        invalid = checkInput(inputString);
        
        while(invalid){
            System.out.print("Invalid: please input an integer\nEnter number of sides on each die: ");
            inputString = input.next();
            invalid = checkInput(inputString);
        }

        gameParams.set(0,Integer.valueOf(inputString));

        System.out.print("Enter number of dice in play: ");
        inputString = input.next();
        invalid = checkInput(inputString);

        while(invalid){
            System.out.print("Invalid: please input an integer\nEnter number of sides on each die: ");
            inputString = input.next();
            invalid = checkInput(inputString);
        }
        gameParams.set(1,Integer.valueOf(inputString));

        System.out.print("Enter number of rolls per hand: ");
        inputString = input.next();
        invalid = checkInput(inputString);

        while(invalid){
            System.out.print("Invalid: please input an integer\nEnter number of sides on each die: ");
            inputString = input.next();
            invalid = checkInput(inputString);
        }
        gameParams.set(2,Integer.valueOf(inputString));

    }

    /**
     * displays all of the parameters for the game
     */
    public void displayParameters(){
        System.out.printf("you are playing with %d %d sided dice",gameParams.get(1),gameParams.get(0));
        System.out.println();
        System.out.printf("You have %d rolls per hand",gameParams.get(2));
        System.out.println();
    }

    /**
     * gets a certain parameter by index
     * @param index index of parameter you want
     * @return value of parameter at index
     */
    public int getIndex(int index){
        return gameParams.get(index);
    }

    /**
     * returns true if the input is an invalid input, checks if the input is an integer, then checks if the input
     * is a >0
     * @param in input string to check
     * @return true if input is invalid, false if input is valid
     */
    private boolean checkInput(String in){
        int stringInt;
        try {
            stringInt = Integer.parseInt(in);
        } catch (NumberFormatException nfe) {
            return true;
        }

        if (stringInt <=0) // 0 or negative values will make the game break, need positive ints grater than 1
            return true;

        return false;

    }


}

