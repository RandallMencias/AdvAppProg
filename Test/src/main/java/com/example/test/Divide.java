package com.example.test;

public class Divide {

    private static int n; // Factorial number to calculate
    private static int numThreads; // Number of threads to test
    private static Pairs[] pairs;

    // constructor
    public Divide(int n, int numThreads) {
        this.n = n;
        this.numThreads = numThreads;
        pairs = new Pairs[numThreads];
        numtask();
    }

    private static void numtask() {
        int[] tasks = new int[numThreads + 1];
        int q = n / numThreads;
        int r = n % numThreads;

        for (int i = 0; i < numThreads; i++) {
            tasks[i] = i * q;
            if (i < r) {
                tasks[i] += i;
            } else {
                tasks[i] += r;
            }
        }
        tasks[numThreads] = n;
        tasks[0] = 0;

        //separate tasjks in pairs into the array of pairs
        for (int i = 0; i < numThreads; i++) {
            pairs[i] = new Pairs(tasks[i] + 1, tasks[i + 1]);
        }

    }

    public int getSize() {
        return pairs.length;
    }

    public static Pairs[] getPairs() {
        return pairs;
    }
}

