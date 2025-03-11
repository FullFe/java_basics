import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ConcurrentSkipListSet;

public class Scrapper  {
    private static ConcurrentSkipListSet<String> links;

    public static ConcurrentSkipListSet<String> getLinks(String url) {
        links = new ConcurrentSkipListSet<>();
        try{
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Connection connection = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .timeout(1000)
                    .followRedirects(false);
            Document doc = connection.get();
            Elements elements = doc.select("body").select("a");
            for (Element element : elements) {
                String href = element.absUrl("href");
                if (isLink(href) && !isFile(href)) {
                    links.add(href);
                }
            }
        } catch (IOException e){
            System.out.println(e + " - " + url);
        }
        return links;
    }


    public static boolean isLink(String link){
        String regex = "http[s]?://[^#,\\s]*\\.?sendel\\.ru[^#,\\s]*";
        return link.matches(regex);
    }

    public static boolean isFile(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestMethod("HEAD");
        httpConn.connect();

        // Получаем заголовки
        String contentType = httpConn.getContentType();
        String contentDisposition = httpConn.getHeaderField("Content-Disposition");

        // Проверяем, является ли содержимое файлом для скачивания
        if (contentDisposition != null && contentDisposition.contains("attachment")) {
            return true;
        }

        // Проверяем Content-Type на наличие типов, которые обычно указывают на файлы для скачивания
        if (contentType != null) {
            return contentType.startsWith("application/") ||
                    contentType.startsWith("audio/") ||
                    contentType.startsWith("video/") ||
                    contentType.startsWith("image/") ||
                    contentType.startsWith("text/plain") ||
                    contentType.startsWith("text/csv") ||
                    contentType.startsWith("application/octet-stream");
        }

        return false;
    }
}
