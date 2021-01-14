/*
Name: Bryan Bennett
Course: CSC201B
Project: KnapSack Greedy and Optimal Fill
Description of this class: Fill class which contains the greedy and optimal fill algos. Greedy navigates the
thing array and picks the thing with the largest value, regardless of weight. Optimal fill uses recursion to
navigate the thing array and pick the best combination of things that optimizes value. These things are then
placed in the knapsack and returned to main.
 */


public class Fill {

    //Variable Declaration
    protected Thing[] things;                       //Array of things
    protected Thing[] knapSack;                     //KnapSack
    protected int capacity;                         //Weight capacity


    //Greedy fill method. Uses loops to navigate thingArray and fill knapsack. Returns knapsack
    public Thing[] greedyFill(Thing[] thingArray, int c){

        Thing[] things = thingArray;
        this.capacity = c;
        int currentWeight = 0;
        int currentValue = 0;
        Thing maxThing;
        int thingsPlaced = 0;
        knapSack = new Thing[thingArray.length];
        int tracker = 0;

        //Continue adding things to knapsack until we have reached capacity or end of thingArray
        while ( (currentWeight < capacity) && (thingsPlaced < knapSack.length) ) {

            //Used to find Max Value for each loop through thingArray
            maxThing = new Thing();

            //Navigate array of things, find thing that has maximum value
            for (int i = 0; i < things.length; i++){

                //If this thing is larger than what maxThing already stores, it hasn't already been placed, and it won't cause it to overflow capacity, update maxThing
                if( ((things[i].getW() + currentWeight) <= capacity) && (things[i].getV() > maxThing.getV()) && !(things[i].isPlaced()))  {
                    maxThing = things[i];
                    tracker = i;
                }
            }

            //MaxThing was found
            if (maxThing.getV() > 0){

                //Set to placed, track sum of Value and Weight of knapSack
                things[tracker].setPlaced(true);
                currentValue += maxThing.getV();
                currentWeight += maxThing.getW();

                //Place maxThing in knapsack
                knapSack[thingsPlaced] = maxThing;

            }

            //Counter. Ensures we do not go past bounds of thingArray
            thingsPlaced++;

        }

        return knapSack;

    }

    //The "main" of the optimum fill process. Sets up variables and calls recursive oFill function
    public Thing[] optimalFill(Thing[] thingArray, int c){

        //Create new thing Array for KnapSack
        knapSack = new Thing[thingArray.length];

        //Create local copy of thingArray
        things = thingArray;

        //Set member variable for capacity
        this.capacity = c;

        //call recursive function
        oFill(0, 0);

        //Fill knapsack with things from boolean values within oFill function
        for (int i = 0; i < things.length; i++){
            if (things[i].isPlaced()){ knapSack[i] = things[i]; }
        }

        //Return knapsack thingArray
        return knapSack;
    }

    //Recursive function that evaluates down tree. Returns optimal value, booleans are used to create knapsack of objects
    public int oFill(int n, int currWeight){

        //If we still have items left and space for additional weight
        if ( (n < things.length) && (currWeight < capacity) ){

            //Evaluate the optimum value of the napsack if you leave item (right) or add it (left)
            int add = oFill(n+1, currWeight + things[n].getW()) + things[n].getV();
            int leave = oFill(n+1, currWeight);

            //If adding the item exceeds max weight, or if it does not have a larger optimum value, return leave
            if( ((currWeight + things[n].getW()) > capacity) || (add <= leave) ){

                //Set location in thing array to false, so we know what is and isn't included in knapsack
                things[n].setPlaced(false);
                return leave;
            }

            //Else, it is best to add it. Mark the boolean value in thingArray as true
            else{
                things[n].setPlaced(true);
                return add;
            }

        }

        //Base case
        else{ return 0; }
    }

}
