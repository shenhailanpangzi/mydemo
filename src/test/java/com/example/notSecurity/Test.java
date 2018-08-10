package com.example.notSecurity;

import java.util.concurrent.ExecutionException;

/**
 * @Description：
 * @Author:yanghao
 * @Date：2018/8/9 15:34
 */
public class Test {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        NotSecurity notSecurity = new NotSecurity();
        notSecurity.go();

    }
    static class Data {
        private int data;// 共享数据

        public synchronized void set(int data) {
            System.out.println(Thread.currentThread().getName() + "准备自增10次");
            for (int i = 0; i <11 ; i++) {
                this.data ++;
            }
            System.out.println(Thread.currentThread().getName() + "写入" + this.data);
        }

        public synchronized void get() {
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "读取" + this.data);
        }

    }
}
