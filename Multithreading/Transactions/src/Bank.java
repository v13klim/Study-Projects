import java.util.HashMap;
import java.util.Random;

public class Bank {

    private final HashMap<String, Account> accounts;
    private final Random random = new Random();

    public Bank() {
        accounts = new HashMap<>();
    }

    public synchronized boolean isFraud()
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */

    public  void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if (getBalance(fromAccountNum) > amount && amount != 0
                && accounts.get(fromAccountNum).isBlock() && accounts.get(toAccountNum).isBlock()) {
            synchronized (accounts) {
                accounts.get(fromAccountNum).setMoney(-amount);
                accounts.get(toAccountNum).setMoney(amount);
            }
            System.out.println("Перевод выполнен со счета №" + fromAccountNum + ", на котором - " + accounts.get(fromAccountNum).getMoney() +
                    ", на счет №" + toAccountNum + ", на котором - " + accounts.get(toAccountNum).getMoney() + ". Сумма транзакции - " + amount);
            if (amount > 50000) {
                try {
                    if (isFraud()) {
                        accounts.get(fromAccountNum).setBlock(true);
                        accounts.get(toAccountNum).setBlock(true);
                        System.out.println("Счета №" + accounts.get(fromAccountNum).getAccountNumber() + ", №"
                                + accounts.get(toAccountNum).getAccountNumber() + " заблокированы");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        int sum = 0;
        for (Account account : accounts.values()) {
            sum += account.getMoney();
        }
        return sum;
    }

    public synchronized Account createAccount(int money) {
        String accountNumber = Integer.toString(accounts.size() + 1);
        Account account = new Account(money, accountNumber);
        accounts.put(accountNumber, account);
        return account;
    }

    public String getRandomNumberAccount(String accountNumber) {
        String newNumber = String.valueOf((int) (Math.random() * (accounts.size() - 1) + 1));
        if (newNumber.equals(accountNumber)) {
            newNumber = null;
        }
        return newNumber;
    }
}