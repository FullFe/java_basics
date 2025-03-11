import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.RecursiveAction;

public class MapRecursiveAction extends RecursiveAction {
    private SiteNode siteNode;
    //Список всех ссылок
    private static CopyOnWriteArrayList linksPool = new CopyOnWriteArrayList();

    public MapRecursiveAction(SiteNode siteNode) {
        this.siteNode = siteNode;
    }

    @Override
    protected void compute() {

        linksPool.add(siteNode.getUrl());
        //Ссылки конкретной ноды
        ConcurrentSkipListSet<String> links = Scrapper.getLinks(siteNode.getUrl());

        for (String link : links) {
            if(!linksPool.contains(link)) {
                linksPool.add(link);
                siteNode.addChild(new SiteNode(link));
            }
        }
        List<MapRecursiveAction> taskList = new ArrayList<>();

        for (SiteNode child : siteNode.getSiteNodeChildrens()) {
            MapRecursiveAction task = new MapRecursiveAction(child);
            task.fork();
            taskList.add(task);
        }

        for (MapRecursiveAction task : taskList) {
            task.join();
        }
    }
}
