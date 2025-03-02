
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.ForkJoinPool;


public class TestClass {
    public static void main(String[] args) {

        String url = "https://sendel.ru/";
        String pathToFile = "Multithreading/MapDraw/SendelMap.txt";

        SiteNode siteNode = new SiteNode(url);

        MapRecursiveAction task = new MapRecursiveAction(siteNode);
        new ForkJoinPool().invoke(task);

        FileOutputStream stream = null;
        try {
            stream = new FileOutputStream(pathToFile);
            String siteMapFile = createMap(siteNode, 0);
            stream.write(siteMapFile.getBytes());
            stream.flush();
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String createMap(SiteNode siteNode, int count){
        String tab = String.join("", Collections.nCopies(count, "\t"));
        StringBuilder result = new StringBuilder(tab + siteNode.getUrl());
        siteNode.getSiteNodeChildrens().forEach(child -> result.append("\n")
                .append(createMap(child, count + 1)));
        return result.toString();
    }
}
