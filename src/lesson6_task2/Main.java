package lesson6_task2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите размер масива:");
        int size = sc.nextInt();

        int[] arr = new int[size];

        initializationMassive(arr);

        System.out.println("Введите количество потоков(должно быть кратно размеру масива)");
        int n = sc.nextInt();

        checkValid(size, n);

        SumMassive[] sumMassives = new SumMassive[n];
        Thread[] threads = new Thread[n];

        int elementOneStream = arr.length / n;

        long start = System.currentTimeMillis();

        creationObjects(sumMassives, arr,elementOneStream);

        creationStream(threads, sumMassives);

        startingThreads(threads);

        long end = System.currentTimeMillis();

        long resultSum = sum(threads, sumMassives);

        System.out.println("Сума массива: " + resultSum);
        System.out.println("Время выполнения: " + (end - start) + " ms");

        System.out.println("Обычный алгоритм:");

        long start2 = System.currentTimeMillis();

        long sum = 0;

        for(int i = 0; i < arr.length; i++){
            sum += arr[i];
        }

        long end2 = System.currentTimeMillis();

        System.out.println("Сума массива: " + sum);
        System.out.println("Время выполнения: " + (end2 - start2) + " ms");


    }

    private static void initializationMassive(int[] arr){
        Random rn = new Random();
        for(int i = 0; i < arr.length; i++){
            arr[i] = rn.nextInt(0, 10);
        }
    }

    private static void checkValid(int size, int n){
         if(size % n != 0){
             System.out.println("Такое количество потоков не может существовать!");
             System.out.println("Программа не может быть выполнена коректно!");
             System.exit(0);
         }
    }

    private static void creationObjects(SumMassive[] sumMassives, int[] arr , int elementOneStream){
        int begin = 0;
        int ending = elementOneStream;

        for(int i = 0; i < sumMassives.length; i++){
            sumMassives[i] = new SumMassive(arr, begin, ending);
            begin = ending;
            ending += elementOneStream;
        }
    }

    private static void creationStream(Thread[] threads, SumMassive[] sumMassives){
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(sumMassives[i]);
            threads[i].start();
        }
    }

    private static void startingThreads(Thread[] threads){
        try {
            for(int i = 0; i < threads.length; i++){
                threads[i].join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static int sum(Thread[] threads ,SumMassive[] sumMassives){
        int result = 0;
        for(int i = 0; i< threads.length; i++){
             result += sumMassives[i].getSum();
        }
        return result;
    }

}
