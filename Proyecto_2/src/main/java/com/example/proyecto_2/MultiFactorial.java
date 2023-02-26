package com.example.proyecto_2;

import javafx.concurrent.Task;

import java.math.BigInteger;

public class MultiFactorial extends Task<BigInteger> {
    private static int n; // Factorial number to calculate
    private static int numThreads; // Number of threads to test
    private static int[] results;

    // constructor
    public MultiFactorial(int n, int numThreads) {
        this.n = n;
        this.numThreads = numThreads;
    }

    // long-running code to be run in a worker thread
    @Override
    protected BigInteger call() {
        updateMessage("Calculating...");
        BigInteger result = factorial();
        System.out.println("Que paso aca");
        updateMessage("Done calculating.");
        return result;
    }

    private static int[] numtask(){
        int[] tasks = new int[numThreads+1];
        int q = n / numThreads;
        int r = n % numThreads;

        for (int i =0; i < numThreads; i++) {
            tasks[i] = i * q;
            if (i < r) {
                tasks[i] += i;
            } else {
                tasks[i] += r;
            }
        }
        tasks[numThreads] = n;
        tasks[0] = 0;
        
        return tasks;
    }



    private static BigInteger multiply1(int start, int end){
        BigInteger f = new BigInteger("1");
        for (int i = start; i <= end; i++){
            f = f.multiply(BigInteger.valueOf(i));}
//            System.out.println(f+" start: "+start+" end: "+end+" i: "+i);}
        return f;
    }

    public static BigInteger factorial()
    {
        BigInteger f = new BigInteger("1");
        int []numtask = numtask();
        for(int i = 0; i < numtask.length-1; i++){
              f = f.multiply(multiply1(numtask[i]+1, numtask[i+1]));
        }
        return f;
    }



}
