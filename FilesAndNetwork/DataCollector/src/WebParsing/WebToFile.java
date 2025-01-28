/**
 * Класс для создания файла с html кодом страницы
 */
package WebParsing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebToFile {

    static boolean isConnected = false;
    /**
     * Если путь пустой, значит файл не создался
     * @return Путь к созданному файлу
     */
    public static String createHtmlFile() {
        String result = "";
        try {
            URL url = new URL("https://skillbox-java.github.io/");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            try (PrintWriter writer = new PrintWriter("FilesAndNetwork/DataCollector/src/WebParsing/SubwayMap.html")) {

                while ((inputLine = reader.readLine()) != null) {
                    writer.println(inputLine);
                }
                result = "FilesAndNetwork/DataCollector/src/WebParsing/SubwayMap.html";
            } catch (IOException e) {
                e.printStackTrace();

            }
            reader.close();

            isConnected = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
