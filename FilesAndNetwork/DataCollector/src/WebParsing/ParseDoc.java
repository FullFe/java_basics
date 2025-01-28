package WebParsing;

import WebParsing.HtmlSubWay.htmlLine;
import WebParsing.HtmlSubWay.htmlStation;
import com.google.common.io.Files;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static WebParsing.WebToFile.createHtmlFile;

public class ParseDoc {
    String path;
    ParseDoc(){
        if (WebToFile.isConnected)
            path = createHtmlFile();
        else
            path = "FilesAndNetwork/DataCollector/src/WebParsing/SubwayMap.html";
        if (path.isEmpty()) {
            path="";
            System.out.println("No HTML file found");
        }
    }

    /**
     * Конструктор для получения html страницы через jsoup
     * @param url
     * @throws IOException
     */
    ParseDoc(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Files.write(doc.toString().getBytes(), new File("FilesAndNetwork/DataCollector/src/WebParsing/SubwayMap2.html"));
        File newFile = new File("FilesAndNetwork/DataCollector/SubwayMap2.html");
        if (newFile.exists() && newFile.length()!=0) {
            path = newFile.getAbsolutePath();
        }else{
            path = "";
        }
    }


    public static String getStringValueOfHtml() {
        String result = "";
       ParseDoc parseDoc = new ParseDoc();
        try{
            Document doc = Jsoup.parse(Path.of(parseDoc.path), "UTF-8");
            System.out.println(doc.head());
            result = doc.head().toString();
            System.out.println(result);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }


    public static List<htmlLine> getSubwayLines(){
        List<htmlLine> result = new ArrayList<>();
        ParseDoc parseDoc = new ParseDoc();
        try{
            Document doc = Jsoup.parse(Path.of(parseDoc.path), "UTF-8");

            Elements elements = doc.getElementsByClass("js-metro-line");
            for (Element element : elements) {
                String number = element.attribute("data-line") == null ? "" : element.attribute("data-line").getValue();

                result.add(new htmlLine(number,element.text()));
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }


    public static List<htmlStation> getSubwayStations(){
        List<htmlStation> result = new ArrayList<>();
        ParseDoc parseDoc = new ParseDoc();
        try{
            Document doc = Jsoup.parse(Path.of(parseDoc.path), "UTF-8");

            Elements lines = doc.getElementsByClass("js-metro-stations t-metrostation-list-table");

            for (Element line : lines) {
                Elements stations = line.children();

                String number = line.attribute("data-line") == null ? "" : line.attribute("data-line").getValue();

//                Elements nameLine = doc.getElementsByClass("js-metro-line t-metrostation-list-header t-icon-metroln ln-" + number);
//
//                //number += " ("+  nameLine.get(0).text() + ")";
                for (Element station : stations) {
                     result.add(new htmlStation(station.getElementsByAttributeValue("class", "name").text(), number));
                }

            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;

    }



}
