package org.example;

public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();
        System.out.println("Main Thread Started");

        Thread th1 = new Thread(()->{
            System.out.println("Thread1 calling produce method");
            resource.produce();
        });

        Thread th2 = new Thread(()->{
            try{
                Thread.sleep(1000);
            }catch(Exception e){
                //some exception handling
            }
            System.out.println("Thread2 is calling the produce method");
            resource.produce();
        });

        //th1 will first acquire the lock as there is 1 sec wait added for th2.
        //th1 acquires lock and will hold it for 8 sec
        //till then from last 7 secs th2 will be waiting to acquire the lock.
        th1.start();
        th2.start();

        //this sleep is on main thread
        //The sleep ensures that th1 starts executing and acquires the lock on SharedResource,
        // and that th2 also gets a chance to try to acquire the lock after its own 1-second sleep.
        //so before th1 gets suspended, th1 acquires the lock, th2 after 1 sec is waiting to acquire the lock
        //since th1 acquired the lock first, it will hold lock for 8 seconds
        //and in between i.e 4th second only, th1 got suspended without releasing lock
        //so th2 will keep on waiting.
        try{
            Thread.sleep(3000);
        }catch(Exception e){
            //some exception handling
        }

        th1.suspend();

        try{
            Thread.sleep(3000);
        }catch(Exception e){
            //some exception handling
        }
        //resuming thread 1
        th1.resume();
        System.out.println("Thread 1 is suspended");

        System.out.println("Main thread completed its task");


    }
}