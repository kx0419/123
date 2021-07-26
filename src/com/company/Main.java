package com.company;

import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Main {

    static CountDownLatch c=new CountDownLatch(1);
    public static void main(String[] args) throws InterruptedException {
        System.out.println("计时开始");
        new Thread(){
            @Override
            public void run() {
                Scanner sc=new Scanner(System.in);
                while (sc.hasNext()){
                    int x=sc.nextInt();
                    System.out.println(x);
                }
                sc.close();
            }
        }.start();
	// write your code here
        for (int i = 1; i <=3 ; i++) {
            c.await(3, TimeUnit.SECONDS);
            System.out.println(i*3+"s");
            new Thread(){
                @Override
                public void run() {
                    Scanner sc=new Scanner(System.in);
                    while (sc.hasNext()){
                        int x=sc.nextInt();
                        System.out.println(x);
                    }
                    sc.close();
                }
            }.start();
        }
    }
}
