package Practice;

public class ThreadDemo {
    public static void main(String[] args) {
        
        // First thread : thread - JHON

        Runnable runnable = () ->{
            for(int i = 1 ; i <= 10 ; i++){
                System.out.println("Value of i is " + i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread  t = new Thread(runnable);
        t.setName("JHON");
        t.start();
    }
}
