package com.example.proyecto_2;

import javafx.concurrent.Task;

import java.math.BigInteger;

public class Factorial extends Task<BigInteger> {
    private final int n; // Factorial number to calculate

    // constructor
    public Factorial(int n) {
        this.n = n;
    }

    // long-running code to be run in a worker thread
    @Override
    protected BigInteger call() {
        updateMessage("Calculating...");
        BigInteger result = factorial(n);
        updateMessage("Done calculating.");
        return result;
    }

    public static BigInteger factorial(int N)
    {
        BigInteger f = new BigInteger("1");

        for (int i = 2; i <= N; i++)
            f = f.multiply(BigInteger.valueOf(i));
        return f;
    }
}

