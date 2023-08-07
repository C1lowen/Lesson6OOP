package lesson6_task2;

import java.util.Arrays;

public class SumMassive implements Runnable{
    private int[] massive;
    private int start;
    private int end;
    private long sum = 0;


    public SumMassive(int[] massive, int start, int end) {
        this.massive = massive;
        this.start = start;
        this.end = end;
    }

    public int[] getMassive() {
        return massive;
    }

    public void setMassive(int[] massive) {
        this.massive = massive;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public long getSum() {
        return sum;
    }

    public void setSum(long sum) {
        this.sum = sum;
    }


    @Override
    public void run() {
        Thread thr = Thread.currentThread();
        for(int i = start; i < end; i++){
            sum += massive[i];
        }
    }

}
