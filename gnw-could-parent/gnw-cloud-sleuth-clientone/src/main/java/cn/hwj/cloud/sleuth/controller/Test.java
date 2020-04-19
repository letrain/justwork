package cn.hwj.cloud.sleuth.controller;

import com.alibaba.fastjson.JSONObject;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Test {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("this is thread");
        }
    }
    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("this is runnable!!!");
        }
    }

    static class MyCall implements Callable {
        @Override
        public Object call() throws Exception {
            System.out.println("this is call interface!!!");
            return "ok";
        }
    }

    public static void main(String[] args) {
        Test.MyThread myThread = new Test.MyThread();
        myThread.start();
        System.out.println(JSONObject.toJSONString(myThread.getState()));
        System.out.println(myThread.getId());
        System.out.println(myThread.isAlive());
        System.out.println(myThread.isDaemon());

        Test.MyRunnable myRunnable = new MyRunnable();
        myRunnable.run();

        MyCall myCall = new MyCall();
        FutureTask futureTask = new FutureTask(myCall);
        Thread futureThread = new Thread(futureTask);
        futureThread.start();
    }
}
