package lesson6_task1;

import java.math.BigInteger;

public class FactorialNumber implements Runnable {

    public FactorialNumber() {
    }

    public BigInteger factorialCalculate(int num) {
        BigInteger fact = BigInteger.ONE;
        for(int i = 1; i <= num; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        return fact;
    }

    @Override
    public void run() {
        Thread thr = Thread.currentThread();
        BigInteger fact = factorialCalculate((int)thr.getId());
        System.out.println(thr.getName() + " id:" + thr.getId() + "  " + thr.getId() + "!= " + fact);
    }
}
