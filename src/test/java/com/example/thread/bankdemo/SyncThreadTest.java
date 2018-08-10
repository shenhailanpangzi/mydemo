package com.example.thread.bankdemo;

/**
 * @Description：
 * @Author:yanghao
 * @Date：2018/8/1 11:27
 */
public class SyncThreadTest {

    public static void main(String args[]){
        final Bank3 bank= new Bank3();
        //存钱
        Thread tadd=new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 0; i <50 ; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    bank.addMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                }
            }
        });
        //取钱
        Thread tsub = new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                for (int i = 0; i <50 ; i++) {
                    bank.subMoney(100);
                    bank.lookMoney();
                    System.out.println("\n");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        });
        tadd.start();
        tsub.start();
    }
}
