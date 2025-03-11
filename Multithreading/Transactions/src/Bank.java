import java.util.Map;
import java.util.Random;

public class Bank {

    private static final long ATTENTION_LIMIT = 50000;
    private Map<String, Account> accounts;
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
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
    public void transfer(String fromAccountNum, String toAccountNum, long amount) {

        if (accounts.get(fromAccountNum) == null || accounts.get(toAccountNum) == null) {
            if(accounts.get("."+fromAccountNum) != null || accounts.get("."+toAccountNum) != null){
                return;
            }
            throw new RuntimeException("Acc not found");
        }
        Account sender = accounts.get(fromAccountNum);
        Account receiver = accounts.get(toAccountNum);


        synchronized (this) {
            long senderMoney = sender.getMoney();
            if (senderMoney < amount) {
                return;
            }
            sender.setMoney(senderMoney - amount);
            receiver.setMoney(receiver.getMoney() + amount);

            if(amount > ATTENTION_LIMIT){
                boolean result;
                try {
                    result = isFraud(fromAccountNum, toAccountNum, amount);
                    if(result){
                        accounts.remove(sender.getAccNumber());
                        sender.setAccNumber("." + sender.getAccNumber());
                        accounts.put(sender.getAccNumber(), sender);

                        accounts.remove(receiver.getAccNumber());
                        receiver.setAccNumber("." + receiver.getAccNumber());
                        accounts.put(receiver.getAccNumber(), receiver);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        synchronized (this) {
            return account.getMoney();
        }
    }

    public synchronized long getSumAllAccounts() {
        long sum = 0;
        for (Account account : accounts.values()) {
            sum += account.getMoney();
        }
        return sum;
    }

    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Map<String, Account> accounts) {
        this.accounts = accounts;
    }
}
