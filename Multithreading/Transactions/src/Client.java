public class Client extends Thread {
    private final Account account;
    private final Bank bank;
    private final String accountNumber;

    public Client(Bank bank) {
        this.bank = bank;
        this.account = bank.createAccount((int) (Math.random() * (200000 - 100000) + 100000));
        this.accountNumber = account.getAccountNumber();
    }

    @Override
    public void run() {
//        System.out.println(
//                Thread.currentThread().getName() + " запущен " );
        for (int i = 0; i < 2; i++) {
            int getMoney = (int) getAmountOfMoney();
            transfer(getMoney);
        }
//        System.out.println(
//                Thread.currentThread().getName() + " завершен " );
    }

    public synchronized void transfer(int money) {
        if (account.getMoney() > money) {
            String toAccountNum = bank.getRandomNumberAccount(accountNumber);
            if (toAccountNum == null) account.setBlock(true);
            else bank.transfer(accountNumber, toAccountNum, money);
        }
    }

    public synchronized long getAmountOfMoney() {
        return (int) (Math.random() * (75000 - 15000) + 15000);
    }
}
