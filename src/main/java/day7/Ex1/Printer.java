package day7.Ex1;

public class Printer extends Thread {
    private Storage storage;

    public Printer(Storage storage) {
        this.storage = storage;
        this.start();
    }

    @Override
    public void run() {
        int counter = -1;
        synchronized (storage) {
            while (true) {
                try {
                    storage.wait(); //B1. đẩy sang counter.
                    counter = storage.getValue(); //B4. get value
                    Thread.sleep(500);
                    System.out.printf("Number: %d \n",counter);
                    storage.notifyAll(); // thông báo.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}