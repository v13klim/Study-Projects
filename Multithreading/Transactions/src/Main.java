import java.util.ArrayList;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args)  {
        Bank bank = new Bank();
        Runtime runtime = Runtime.getRuntime();
        int nrOfProcessors = runtime.availableProcessors();
        ArrayList<Thread> threads = new ArrayList<>();

        for (int i = 0; i < nrOfProcessors; i++) {
            threads.add(new Thread(new Client(bank)));
        }

        System.out.println("Средств в банке до транзакций " + bank.getSumAllAccounts());
        
        threads.forEach(thread -> thread.start());
        threads.forEach(thread -> {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });



        System.out.println("Средств в банке после транзакций " + bank.getSumAllAccounts());
    }
}
