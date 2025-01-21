import CustomExceptions.IncorrectInputDataException;
import CustomExceptions.WrongNumOfParamException;

import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws RuntimeException{
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String regex1 = "[а-яА-ЯёЁ]+";
        String regex2 = "[a-zA-Z0-9.]+@[a-zA-Z]+\\.[a-zA-Z]+";
        String regexNumber="\\+7[0-9]+";

        String[] components = data.split("\\s+");
        if (!(components.length == 4)){
            throw new WrongNumOfParamException("Wrong number of parameters in add method");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];

        if(!(components[INDEX_NAME].matches(regex1) && components[INDEX_SURNAME].matches(regex1))){
            throw new IncorrectInputDataException("Incorrect name/surname format");
        }
        if(!(components[INDEX_EMAIL].matches(regex2))){
            throw new IncorrectInputDataException("Incorrect email format");
        }
        if(!(components[INDEX_PHONE].matches(regexNumber) && components[INDEX_PHONE].length()== 12)){
            throw new IncorrectInputDataException("Incorrect phone format");
        }
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}