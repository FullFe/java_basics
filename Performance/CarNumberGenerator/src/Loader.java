import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

public class Loader {

    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        //FileOutputStream writer = new FileOutputStream("CarNumberGenerator/res/numbers.txt");
        int[] numbers = {1, 2, 3, 4};
        Arrays.stream(numbers)
            .parallel() // Включаем параллельную обработку
            .forEach(i -> {
                try {
                    saveFiles(i, start);
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
        });


        //System.out.println((System.currentTimeMillis() - start) + " ms");
    }
    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);
        return (numberLength - numberStr.length())<0 ? numberStr :"0".repeat(numberLength - numberStr.length()) + numberStr;
    }

    private static void saveFiles(int numberOfFile, long start) throws FileNotFoundException {
        String nameFile = "CarNumberGenerator/res/numbers" + numberOfFile + ".txt";
        PrintWriter writer = new PrintWriter(nameFile);
        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};
        for (int number = 1; number < 1000; number++) {
            int regionCode = 199;
            for (char firstLetter : letters) {
                for (char secondLetter : letters) {
                    for (char thirdLetter : letters) {
                        String sb = firstLetter +
                                padNumber(number, 3) +
                                secondLetter +
                                thirdLetter +
                                padNumber(regionCode, 2) +
                                '\n';
                        writer.write(sb);
                    }
                }
            }

        }
        writer.flush();
        writer.close();
        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
