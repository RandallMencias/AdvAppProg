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
    //calculate runtime
    private long startTime;
    private long endTimeT;
    private static BigInteger result = new BigInteger("1");
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
//
        }
        endTimeT = System.currentTimeMillis();
        updateMessage(f.toString()+"\t tiempo: " + Long.toString(endTimeT-startTime) + " ms");


        return f;

    }
    //function to get runTime
    public long getRunTime(){
        return (endTimeT - startTime);
    }


}

