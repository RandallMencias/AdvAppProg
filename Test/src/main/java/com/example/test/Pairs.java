package com.example.test;

public class Pairs {
    private int start;
    private int end;


    public Pairs(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int[] getpair() {
        int[] pair = {start, end};
        return pair;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void printpair() {
        System.out.println("key: "+ start +" value: "+ end);}

    }

