package com.example.thread.lock;

import java.util.Random;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description：
 * 读写锁(ReadWriteLock)，我们会有一种需求，在对数据进行读写的时候，为了保证数据的一致性和完整性，需要读和写是互斥的，写和写是互斥的，
 * 但是读和读是不需要互斥的，这样读和读不互斥性能更高些，来看一下不考虑互斥情况的代码原型：
 * @Author:yanghao
 * @Date：2018/7/31 10:41
 */
public class LockDemo {
    public static void main(String[] args) {
        final Data2 data = new Data2();
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.set(new Random().nextInt(30));
                    }
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                public void run() {
                    for (int j = 0; j < 5; j++) {
                        data.get();
                    }
                }
            }).start();
        }
    }

    static class Data {
        private int data;// 共享数据

        public synchronized void set(int data) {
            System.out.println(Thread.currentThread().getName() + "准备写入数据");
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.data = data;
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
//    用读写锁实现代码
static class Data2 {
        private int data;// 共享数据
        private ReadWriteLock rwl = new ReentrantReadWriteLock();
        public void set(int data) {
            rwl.writeLock().lock();// 取到写锁
            try {
                System.out.println(Thread.currentThread().getName() + "准备写入数据");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                this.data = data;
                System.out.println(Thread.currentThread().getName() + "写入" + this.data);
            } finally {
                rwl.writeLock().unlock();// 释放写锁
            }
        }
        public void get() {
            rwl.readLock().lock();// 取到读锁
            try {
                System.out.println(Thread.currentThread().getName() + "准备读取数据");
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "读取" + this.data);
            } finally {
                rwl.readLock().unlock();// 释放读锁
            }
        }
    }
}
