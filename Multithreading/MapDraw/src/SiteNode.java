import java.util.concurrent.CopyOnWriteArrayList;

public class SiteNode {
    private String url;

    private CopyOnWriteArrayList<SiteNode> siteNodeChildrens;

    public SiteNode(String url) {
        this.url = url;
        this.siteNodeChildrens = new CopyOnWriteArrayList<>();
    }


    public void addChild(SiteNode child) {
        siteNodeChildrens.add(child);
    }

    public CopyOnWriteArrayList<SiteNode> getSiteNodeChildrens() {
        return siteNodeChildrens;
    }

    public String getUrl() {
        return url;
    }

}
