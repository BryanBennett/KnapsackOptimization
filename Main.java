/*
Name: Bryan Bennett
Course: CSC201B
Project: KnapSack Greedy and Optimal Fill
Description of this class: This main class reads the knapsack.txt file and creates an array of thing objects. An object
of the fill class is created, where the thingArray is sent to greedy fill and optimal fill classes.
The main then navigates the returned knapsacks and prints the relevant information.
 */


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        //Open file and create input file stream
        FileInputStream things = new FileInputStream("knapsack.txt");

        //Create scanner to read in text file
        Scanner scanner = new Scanner(things);

        //Read in capacity and number of things from text file
        int capacity = scanner.nextInt();
        int numThings = scanner.nextInt();

        //Create thingArray
        Thing[] thingArray = new Thing[numThings];

        //Create numThing things in thingArray, set weights
        for(int i = 0; i < numThings; i++){
            thingArray[i] = new Thing();
            thingArray[i].setW(scanner.nextInt());
        }

        //Now set Value for thing objects in array
        for(int i = 0; i < numThings; i++){
            thingArray[i].setV(scanner.nextInt());
        }

        //Print array of things
        System.out.println("Printing array of things collected from txt file:");

        for(int i = 0; i < numThings; i++){
            System.out.printf("Object %d: Value = %d, Weight = %d\n", i, thingArray[i].getV(), thingArray[i].getW());
        }


        //Call fill class, create object
        Fill knapSack = new Fill();


        //Call greedyKnapSack method, which returns a knapsack
        Thing[] greedyKnapSack = knapSack.greedyFill(thingArray, capacity);

        //Track the sum of Value and Weight
        int sumV = 0;
        int sumW = 0;

        System.out.printf("\nPrinting Greedy KnapSack\n");

        //Navigate greedy knapsack, print the things that are in knapsack, track total value and weight
        for(int i = 0; i<greedyKnapSack.length; i++){

            if(greedyKnapSack[i] != null){
                sumV += greedyKnapSack[i].getV();
                sumW += greedyKnapSack[i].getW();

                if (greedyKnapSack.length < 15){
                    System.out.printf("Object %d placed. Value = %d, Weight = %d\n", i, greedyKnapSack[i].getV(), greedyKnapSack[i].getW());
                }

            }
            else{
                if (greedyKnapSack.length < 15) { System.out.printf("Object %d NOT placed\n", i); }
            }
        }

        //Print total value and weight of knapsack
        System.out.printf("Total Value = %d, Total Weight = %d\n\n", sumV, sumW);


        //Call optimal KnapSack method, which returns a knapsack
        Thing[] optimalKnapSack = knapSack.optimalFill(thingArray, capacity);

        sumV = 0;
        sumW = 0;

        System.out.printf("Printing Optimal KnapSack\n");

        //Navigate optimal knapsack, print the things that are in knapsack, track total value and weight
        for(int i = 0; i<optimalKnapSack.length; i++){

            if(optimalKnapSack[i] != null){
                sumV += optimalKnapSack[i].getV();
                sumW += optimalKnapSack[i].getW();

                if (optimalKnapSack.length < 15) {
                    System.out.printf("Object %d placed. Value = %d, Weight = %d\n", i, optimalKnapSack[i].getV(), optimalKnapSack[i].getW());
                }

            }
            else{
                if (optimalKnapSack.length < 15) { System.out.printf("Object %d NOT placed\n", i); }
            }

        }

        //Print total Value and Weight from Optimal Fill
        System.out.printf("Total Value = %d, Total Weight = %d\n\n", sumV, sumW);

    }
}
