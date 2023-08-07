package lesson6_task1;

public class Main {
    public static void main(String[] args) {

        FactorialNumber[] tasks = new FactorialNumber [100];
        Thread[] threads = new Thread[100];

        for(int i = 0; i < 100; i++){
            tasks[i] = new FactorialNumber();
        }

        for(int i = 0; i < 100; i++){
            threads[i] = new Thread(tasks[i]);
            threads[i].start();
        }


    }
}
