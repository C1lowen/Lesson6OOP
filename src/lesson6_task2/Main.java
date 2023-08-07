package lesson6_task2;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rn = new Random();
        int[] arr = new int[1000000];

        for(int i = 0; i < arr.length; i++){
            arr[i] = rn.nextInt(20, 100);
        }


        long start = System.nanoTime();

        SumMassive first = new SumMassive(arr, 0, 250000);
        SumMassive second = new SumMassive(arr, 250000, 500000);
        SumMassive third = new SumMassive(arr, 500000, 750000);
        SumMassive fourth = new SumMassive(arr, 750000, 1000000);

        Thread th1 = new Thread(first);
        Thread th2 = new Thread(second);
        Thread th3 = new Thread(third);
        Thread th4 = new Thread(fourth);

        th1.start();
        th2.start();
        th3.start();
        th4.start();

        try {
            th1.join();
            th2.join();
            th3.join();
            th4.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        long end = System.nanoTime();

        long resultSum = first.getSum() + second.getSum() + third.getSum() + fourth.getSum();

        System.out.println("Сума массива: " + resultSum);
        System.out.println("Время выполнения: " + (end - start));

        long start2 = System.nanoTime();

        int sum = 0;

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }

        long end2 = System.nanoTime();

        System.out.println("Сума массива: " + sum);
        System.out.println("Время выполнения: " + (end2 - start2));


    }
}
