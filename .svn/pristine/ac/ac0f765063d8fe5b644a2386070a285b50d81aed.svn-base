package com.oio.wawj.util;

import java.util.ArrayList;
import java.util.List;

public class TestRunnable implements Runnable{

	private int time=1;
    private SourceA s ;
    private String id = "001";
    public TestRunnable(SourceA s){
        this.s = s;
    }
    public void setTime(int time) {
        this.time = time;
    }
    public static void test(){
        SourceA s = new SourceA();
        TestThread tt = new TestThread(s);
        TestRunnable tr = new TestRunnable(s);
        Thread t = new Thread(tr);
        System.out.println("调用线程1");
        tt.start();
        System.out.println("调用线程2");
        t.start();
    } 
    @Override
    public void run() {
        try {
            System.out.println("i will sleep"+ time);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        synchronized(s){
            s.notify();
            System.out.println("我唤醒了002！");
            System.out.println("我存入了id"+id);
            s.setSource(id);
        }
    }
    public static void main(String[] arg){
    	test();
    }
}
 class SourceA {
    private List<String> list = new ArrayList<String>();
    public synchronized void getSource(){
        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }
    }
    public synchronized void setSource(String id){
        list.add(id);
    }
 }
 class TestThread extends Thread {
        private int time = 1;
        private SourceA s = null;
        String id = "002";
        
        public void setTime(int time) {
            this.time = time;
        }
        
        public TestThread(SourceA s){
            this.s = s ;
        }
        
        @Override
        public void run() {
            try {
                System.out.println("i will sleep"+ time);
                sleep(time);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            synchronized(s){
                try {
                    System.out.println("我"+ id +"要进行等待了");
                    s.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println("我被唤醒了");
                System.out.println("我存入了id"+id);
                s.setSource(id);
            }
        }
       
        
       

   
}