package day7.Ex1;

public class Counter extends Thread{
    public day7.Ex1.Storage storage;

    public Counter(day7.Ex1.Storage storage) {
        this.storage = storage;
        this.start();
    }

    @Override
    public void run() {
        for (int i = day7.Ex1.Main.MIN_V; i < day7.Ex1.Main.MAX_V; i++) {
            synchronized (storage) {
                try {
                    storage.setValue(i);//B2. setValue
                    storage.notifyAll(); //B3. thông báo và chuyển monitor về Printer.
                    storage.wait(); // B4. Đặt vào trạng thái Wait
                } catch (InterruptedException ie) {
                    System.out.println(ie.getMessage());
                }
            }
        }

    }
}