import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class TestClass {
    public static void main(String[] args) {

        Bank bebraBank = new Bank();

        bebraBank.setAccounts(initAccounts());

        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i<10;i++){
            threads.add(new Thread(()->{
                System.out.println("St "+bebraBank.getSumAllAccounts());
                for(int j =0;j<bebraBank.getAccounts().size();j++){
                    int randomNumber = ThreadLocalRandom.current().nextInt(100);
                    int randomNumberTwo = ThreadLocalRandom.current().nextInt(100);
                    long sum = ThreadLocalRandom.current().nextLong(52500);
                    if(randomNumber == randomNumberTwo){
                        continue;
                    }
                    try{
                        bebraBank.transfer(String.valueOf(randomNumber), String.valueOf(randomNumberTwo), sum);
                    }catch(RuntimeException e){
                        e.printStackTrace();
                    }

                }
                System.out.println("En "+ bebraBank.getSumAllAccounts());


            }));
        }
        for(Thread thread : threads){
            thread.start();
        }
    }

    public static Map<String, Account> initAccounts() {
        Map<String, Account> accounts = new HashMap<>();
        for (int i = 0; i < 100; i++) {
            accounts.put(String.valueOf(i), new Account(1_000_000,String.valueOf(i) ));
        }
        return accounts;
    }
}
