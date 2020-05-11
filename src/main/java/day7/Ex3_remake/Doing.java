package day7.Ex3_remake;

public class Doing extends Thread{
    int size;
    int min;
    CountDivisor countDivisor;

    public Doing(CountDivisor divisor,int size,int min) {
        this.countDivisor = divisor;
        this.size =size;
    }

    @Override
    public void run() {
        int count =0;
        for(int i =min;i<=size;i++){
           count = countDivisor.getUC(i);
           if(count >= countDivisor.getCount_before()){
               countDivisor.setCount_before(count);
               countDivisor.setMax_value(i);
           }
           System.out.println(countDivisor.getCount_before());
        }
    }
}
