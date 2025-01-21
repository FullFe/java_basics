import CustomExceptions.IncorrectInputDataException;
import CustomExceptions.WrongNumOfParamException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

public class Main {
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    private static final Logger querryLogger = LogManager.getLogger("QueryLogger");
    private static final Logger errorLogger = LogManager.getLogger("ErrorLogger");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();
        querryLogger.info("Starting main thread");
        while (true) {
            String command = scanner.nextLine();
            String[] tokens = command.split("\\s+", 2);

            if (tokens[0].equals("add")) {
                try{
                    executor.addCustomer(tokens[1]);
                    querryLogger.debug("Customer added: {}", tokens[1]);
                }catch (WrongNumOfParamException | IncorrectInputDataException  | ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    errorLogger.error(e.getMessage());
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    String stackTraceString = sw.toString();
                    errorLogger.error(stackTraceString);
                }
            } else if (tokens[0].equals("list")) {
                executor.listCustomers();
                querryLogger.debug("List showed to user");
            } else if (tokens[0].equals("remove")) {
                try{
                    executor.removeCustomer(tokens[1]);
                    querryLogger.debug("Customer deleted: {}", tokens[1]);
                }catch (IncorrectInputDataException  | ArrayIndexOutOfBoundsException e){
                    e.printStackTrace();
                    errorLogger.error(e.getMessage());
                    StringWriter sw = new StringWriter();
                    e.printStackTrace(new PrintWriter(sw));
                    String stackTraceString = sw.toString();
                    errorLogger.error(stackTraceString);
                }

            } else if (tokens[0].equals("count")) {
                System.out.println("There are " + executor.getCount() + " customers");
                querryLogger.debug("Amount of customers in the list: {}", executor.getCount());
            } else if (tokens[0].equals("help")) {
                System.out.println(helpText);
                querryLogger.info("Main thread exits");
            } else if ("stop".equals(tokens[0])) {
                querryLogger.info("Stopping main thread");
                break;
            }else {
                System.out.println(COMMAND_ERROR);
                querryLogger.warn("User look for help");
            }
        }
    }
}
