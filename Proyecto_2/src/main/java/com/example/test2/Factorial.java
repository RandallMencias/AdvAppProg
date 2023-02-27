package com.example.test2;

import javafx.concurrent.Task;

import java.math.BigInteger;

public class Factorial extends Task<BigInteger> {
    private BigInteger f = new BigInteger("1");
    private int start;
    private int end;

    public Factorial(int start, int end) {
        this.start = start;
        this.end = end;

    }
    @Override
    protected BigInteger call()  {
        BigInteger f = new BigInteger("1");
        for (int i = start; i <= end; i++) {
            System.out.println(f + "*" + i);
            f = f.multiply(BigInteger.valueOf(i));
            updateMessage(f.toString()+ "*" + Integer.toString(i));
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return f;


    }    }


