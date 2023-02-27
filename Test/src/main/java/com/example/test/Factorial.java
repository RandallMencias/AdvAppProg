package com.example.test;


import javafx.concurrent.Task;

import java.math.BigInteger;
//class to calculate factorial and its runtime
public class Factorial extends Task<BigInteger> {

    private BigInteger f = new BigInteger("1");
    private int start;
    private int end;
    //calculate runtime
    long startTime = System.currentTimeMillis();
    long endTimeT;
    //constructor
    public Factorial(int start, int end) {
        this.start = start;
        this.end = end;

    }
    //function to get factorial
    @Override
    protected BigInteger call() {
        BigInteger f = new BigInteger("1");
        for (int i = start; i <= end; i++) {
            
            f = f.multiply(BigInteger.valueOf(i));
            updateMessage(f.toString() + "*" + Integer.toString(i));
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        endTimeT = System.currentTimeMillis();
        return f;

    }
    //function to get runTime
    public long getRunTime(){
        return (endTimeT - startTime);
    }
}


