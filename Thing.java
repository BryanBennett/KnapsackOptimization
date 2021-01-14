/*
Name: Bryan Bennett
Course: CSC201B
Project: KnapSack Greedy and Optimal Fill
Description of this class: Thing objects. These have value and weight parameters, which are assigned from main.
A boolean "placed" is also included, which is used to track what is and is not placed in the knapsack.
 */


public class Thing {

    //Variable declarations
    protected int W;
    protected int V;
    boolean placed;

    //Constructors
    public Thing(){
        this.W = 0;
        this.V = 0;
        placed = false;
    }
    public Thing(int W, int V){
        this.W = W;
        this.V = V;
        placed = false;
    }

    //Methods
    public int getW(){ return W; }
    public int getV(){ return V; }
    public void setW(int W){ this.W = W; }
    public void setV(int V){ this.V = V; }

    public boolean isPlaced(){ return placed; }
    public void setPlaced(boolean placed){ this.placed = placed; }

}
