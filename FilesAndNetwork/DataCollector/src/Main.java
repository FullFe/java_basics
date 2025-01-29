import java.util.List;

import static FileParsing.FileFinder.find;
import static WebParsing.ParseDoc.*;

/**
 * Класс для проверки всех остальных классов
 */


public class Main {
    public static String SKIPLINE = "##################################################################################\n";
    public static String PATHTOFOLDER = "C:\\Users\\alexp\\IdeaProjects\\java_basics\\FilesAndNetwork\\DataCollector\\src\\FileParsing\\station_data";
    //TODO Методы для разных классов + остальные таски
    public static void main(String[] args) {
        // Проверка 1 класса

        System.out.println("Часть разметки сайта:\n");
        getStringValueOfHtml();
        System.out.println(SKIPLINE);
        System.out.println("Вывод линий с сайта\n");
        getSubwayLines().forEach(System.out::println);
        System.out.println(SKIPLINE);
        System.out.println("Вывод станций с сайта\n");
        getSubwayStations().forEach(System.out::println);
        System.out.println(SKIPLINE);
        // Проверка 2 класса

        System.out.println("Вывод файлов с информацией о них\n");
        List<List<String>> paths = find(PATHTOFOLDER);
        System.out.println("Вывод путей к файлам csv\n");
        paths.get(0).forEach(System.out::println);// Csv files
        System.out.println(SKIPLINE);
        System.out.println("Вывод путей к файлам json\n");
        paths.get(1).forEach(System.out::println); //Json files

        // Проверка 3 класса
        System.out.println(SKIPLINE);
        System.out.println("Вывод объектов созданных из json файла\n");
        FileParsing.ParseFileJson.parseJSON(paths.get(0).get(0)).forEach(System.out::println);

        // Проверка 4 класса
        System.out.println(SKIPLINE);
        System.out.println("Вывод объектов созданных из csv файла\n");
        FileParsing.ParseFileCsv.parseCsv(paths.get(1).get(0)).forEach(System.out::println);

        // Проверка 5 класса
        System.out.println(SKIPLINE);
        System.out.println("Создание первого файла json");
        JsonCreate.firstJson(getSubwayStations(),  getSubwayLines());
        System.out.println("Создание второго файла json");
        JsonCreate.secondJson(getSubwayStations(), getSubwayLines(), FileParsing.ParseFileJson.parseJSON(paths.get(0).get(0)), FileParsing.ParseFileCsv.parseCsv(paths.get(1).get(0)));
    }
}
