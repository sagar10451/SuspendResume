package org.example;

public class SharedResource {
    boolean isAvailable = false;

    public synchronized void produce(){
        System.out.println("Lock Acquired");
        isAvailable=true;
        try{
            Thread.sleep(8000);
        }catch(Exception e){
            //some exception handling
        }
        System.out.println("Lock Released");
    }
}
