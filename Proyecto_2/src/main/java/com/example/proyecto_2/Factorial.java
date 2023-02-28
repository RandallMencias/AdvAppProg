package com.example.proyecto_2;

import javafx.concurrent.Task;
import javafx.scene.control.Label;

import java.math.BigInteger;
import java.util.concurrent.Future;

//class to calculate factorial and its runtime
public class Factorial extends Task<BigInteger> {

    private BigInteger f = new BigInteger("1");
    private int start;
    private int end;
    private static BigInteger result = new BigInteger("1");
    private String time;
    //calculate runtime
    private long startTime;
    private long endTimeT;
    
    //constructor
    public Factorial(int start, int end) {
        this.start = start;
        this.end = end;

    }
    //function to get factorial
    @Override
    protected BigInteger call() {
        startTime = System.currentTimeMillis();
        BigInteger f = new BigInteger("1");
        //for loop to get the  factorial while updating the values at the GUI
        for (int i = start; i <= end; i++) {
            f = f.multiply(BigInteger.valueOf(i));
            updateMessage(f.toString() + "*" + Integer.toString(i));
            updateValue(f);

            //add sleep to see the progress
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateProgress(i,end); 
        }
        //update gui labels
        endTimeT = System.currentTimeMillis();
        time = f.toString()+"\t tiempo: " + Long.toString(endTimeT-startTime) + " ms";
        updateMessage(time); 
        return f;

    }
    //function to get runTime
    public String getRunTime(){
        return time;
    }


}