import java.util.Scanner;

/**
 * This program handles the scorecard for the game. It scores the hand, displays the possible scores, and in the future
 * it will keep track of the scores for the whole game.
 * CPSC 224, Spring 2020
 * Homework 3
 * Sources: Worbrec's c++ code for logic on how to score a hand
 *
 * @author Nathan Magrogan
 * @version v1.8 2/28/2020
 */
public class scoreCard {
    //atributes
    private int[] scores; //not used yet, just planning on using it for future assignmetns.
    private int[] tempScores;
    private int numLines;
    private String[] validStrings;

    /**
     * Creates a new scorecard, intializes scores and temp scores based on number of sides on each die
     * sets scores values to -1, this is how I am denoting and open score line opposed to one with a scoore of 0
     * inatilizes all of the valid strings for the codes for each line
     * @param myHand hand that is going to be scored, necissiary to make it expand for different number of
     *               sided dice
     */
    public scoreCard(hand myHand){
        numLines = myHand.getDie(0).getNumSides() + 7;
        scores = new int[numLines]; //not used yet, just planning on using it for future assignmetns.
        tempScores = new int[numLines];
        for(int i = 0; i< scores.length; i++){
            scores[i]= -1; //setting values to -1 to distinguish between a unscored line and a score of 0
        }


        //initalize possible codes for each line based on number of lines
        validStrings = new String[numLines];

        for(int i = 0; i < numLines-7 ;i++ ){
            validStrings[i] =  String.valueOf(i+1);
        }
        validStrings[numLines-7] = "3ofk";
        validStrings[numLines-6] = "4ofk";
        validStrings[numLines-5] = "fh";
        validStrings[numLines-4] = "ss";
        validStrings[numLines-3] = "ls";
        validStrings[numLines-2] = "y";
        validStrings[numLines-1] = "c";
    }

    //methods
    /**
     * prints all of the possible scores stored in tempScores
     */
    public void displayPossibleScores(){
        int numSides = numLines - 7;
        for(int i = 0; i < numSides ;i++ ){
            if(scores[i] == -1) { //will only print the possible score if no score is logged in the scorecard
                System.out.printf("Score %d on the %d line", tempScores[i], i + 1);
                System.out.println();
            }
        }

        for(int i = numSides; i < tempScores.length;i++){
            if(scores[i] == -1) {//will only print the possible score if no score is logged in the scorecard
                System.out.print("Score ");
                System.out.print(tempScores[i]);
                System.out.print(" on the ");

                if (i == numSides) {
                    System.out.println("3OfK line");
                } else if (i == numSides + 1) {
                    System.out.println("4OfK line");
                } else if (i == numSides + 2) {
                    System.out.println("FH line");
                } else if (i == numSides + 3) {
                    System.out.println("SS line");
                } else if (i == numSides + 4) {
                    System.out.println("LS line");
                } else if (i == numSides + 5) {
                    System.out.println("Y line");
                } else {
                    System.out.println("C line");
                }
            }


        }

    }

    /**
     * Scores a hand, takes a hand of dice and fills tempscores with all of the possible scores
     * @param myHand hand of dice to be scored
     */
    public void scoreHand(hand myHand){
        int numSides = myHand.getDie(0).getNumSides();
        //scoring upper part of the score card
        for (int dieValue = 1; dieValue <= numSides; dieValue++) {
            int currentCount = 0;
            for (int diePosition = 0; diePosition < myHand.getHandSize(); diePosition++)
            {
                if (myHand.getIndex(diePosition) == dieValue)
                    currentCount++;
            }
            tempScores[dieValue-1] = dieValue * currentCount;
        }

        //score lower part of the scorecard
        if (maxOfAKindFound(myHand) >= 3)
            tempScores[numSides] = totalAllDice(myHand);

        if (maxOfAKindFound(myHand) >= 4)
            tempScores[numSides+1] = totalAllDice(myHand);

        if (fullHouseFound(myHand))
            tempScores[numSides+2] = 25;

        if (maxStraightFound(myHand) >= 4)
            tempScores[numSides+3] = 30;

        if (maxStraightFound(myHand) >= 5)
            tempScores[numSides+4] = 40;

        if (maxOfAKindFound(myHand) >= 5)
            tempScores[numSides+5] = 50;

        tempScores[numSides+6] = totalAllDice(myHand);

    }


    /**
     * checks which die value occurred the most in the hand
     * @param myHand hand of dice to check
     * @return the count of the die value occurring most in the hand.
     */
    private int maxOfAKindFound(hand myHand){
        int maxCount = 0;
        int currentCount ;
        for (int dieValue = 1; dieValue <=6; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < myHand.getHandSize(); diePosition++)
            {
                if (myHand.getIndex(diePosition) == dieValue)
                    currentCount++;
            }
            if (currentCount > maxCount)
                maxCount = currentCount;
        }
        return maxCount;

    }

    /**
     * checks if the hand contains a full house.
     * @param myHand hand of dice to be checked.
     * @return return true if a fullhouse is found, false if one is not found.
     */
    private boolean fullHouseFound(hand myHand){
        boolean foundFH = false;
        boolean found3K = false;
        boolean found2K = false;
        int currentCount ;
        for (int dieValue = 1; dieValue <=6; dieValue++)
        {
            currentCount = 0;
            for (int diePosition = 0; diePosition < myHand.getHandSize(); diePosition++)
            {
                if (myHand.getIndex(diePosition) == dieValue)
                    currentCount++;
            }
            if (currentCount == 2)
                found2K = true;
            if (currentCount == 3)
                found3K = true;
        }
        if (found2K && found3K)
            foundFH = true;
        return foundFH;
    }

    /**
     * returns the max lenght of a straight found in a hand
     * @param myHand hand to be checked
     * @return returns the max lenght of a straight found, eg. 2 if there is a straight of 2, 3 if there is a straight of 3 etc.
     */
    private int maxStraightFound(hand myHand){
        int maxLength = 1;
        int curLength = 1;
        for(int counter = 0; counter < myHand.getHandSize()-1; counter++)
        {
            if (myHand.getIndex(counter) + 1 == myHand.getIndex(counter +1)) //jump of 1
                curLength++;
            else if (myHand.getIndex(counter) + 1 < myHand.getIndex(counter+1)) //jump of >= 2
                curLength = 1;
            if (curLength > maxLength)
                maxLength = curLength;
        }
        return maxLength;
    }

    /**
     * totals the value of all of the dice in the hand.
     * @param myHand hand of dice to be totaled
     * @return sum of all of the values of all the dice in the hand.
     */
    private int totalAllDice(hand myHand){
        int total = 0;
        for (int diePosition = 0; diePosition < myHand.getHandSize(); diePosition++)
        {
            total += myHand.getIndex(diePosition);
        }
        return total;
    }

    /**
     * clears all of the scores stored in the tempscores
     */
    private void clearTempScores(){
        for(int i = 0; i < tempScores.length ;i++){
            tempScores[i] = 0;
        }
    }

    /**
     * Prints all of the current score card with all the scores that have been added to it
     */
    public void displayScoreCard(){

        int numSides = numLines - 7;

        for(int i = 0; i < numSides ;i++ ){
            if (scores[i] == -1){
                System.out.printf("%d 's line: _", i+1);
                System.out.println();
            }
            else{
                System.out.printf("%d 's line: %d", i+1,scores[i]);
                System.out.println();
            }
        }

        for(int i = numSides; i < numLines;i++){
            if(i == numSides){
                System.out.print("3 of a kind line: ");
            }
            else if(i == numSides+1){
                System.out.print("4 of a kind line: ");
            }
            else if(i == numSides+2){
                System.out.print("Full house line: ");
            }
            else if(i == numSides+3){
                System.out.print("Small Straight line: ");
            }
            else if(i == numSides+4){
                System.out.print("Large Straight line: ");
            }
            else if(i == numSides+5){
                System.out.print("Yahtzee line: ");
            }
            else {
                System.out.print("Chance line: ");
            }
            if(scores[i] == -1){
                System.out.println("_");
            }else {
                System.out.println(scores[i]);
            }

        }

    }

    /**
     * asks the user which line they want to score for the current hand
     */
    public void chooseLineToScore(){
        boolean invalid = false;
        Scanner input = new Scanner(System.in);

        System.out.print("Enter code of line you want to score: ");
        String keepLineCode = input.next();
        keepLineCode = keepLineCode.toLowerCase();
        invalid = checkInput(keepLineCode);

        while(invalid){
            System.out.print("Invalid entry, Please enter code of line you want to score: ");
            keepLineCode = input.next();
            keepLineCode =  keepLineCode.toLowerCase();
            invalid = checkInput(keepLineCode);
        }

        scoreLine(keepLineCode);

    }

    /**
     * scores line that user wants to score
     * @param input sting containing the line code for the line the user wants to score.
     */
    private void scoreLine(String input){

        for(int i =0; i < numLines;i++){
            if (input.equals(validStrings[i])){
                scores[i] = tempScores[i];
            }
        }
        //resets the temp scores for the next turn
        clearTempScores();


    }

    /**
     * cheks input for the line code, checks to see if what was inputed is one of the valid line codes that were defined
     * in the constructor.
     * @param input sting containing what should be a line code for the score card
     * @return true if input is invalid, false if input is valid.
     */
    private boolean checkInput(String input){
        for (int i = 0;i < numLines;i++){
            if(input.equals(validStrings[i]) && scores[i] == -1){ //second arg keeps player from over writting a score on a line that has already been used.
                return false;
            }
        }
        return true;
    }

    /**
     * reutns the number of lines in the scorecard
     * @return number of lines on the scorecard
     */
    public int getNumLines(){
        return numLines;
    }


}

