package App.Services;

import App.NewsEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NewsFeedService implements CRUDService<NewsEntity> {


    private final ConcurrentHashMap<Long, NewsEntity> news= new ConcurrentHashMap<>();

    @Override
    public NewsEntity getById(long id) {
        return news.get(id);
    }

    @Override
    public Collection<NewsEntity> getAll() {
        return news.values();
    }

    @Override
    public void create(NewsEntity newsEntity) {

        String tmpTitle = newsEntity.getTitle();
        String tmpText = newsEntity.getText();
        if(news.isEmpty()){
            newsEntity.setId(1);
            tmpTitle += " 1";
            tmpText += " 1";

            newsEntity.setTitle(tmpTitle);
            newsEntity.setText(tmpText);
            news.put(1L,newsEntity);
        }else{
            Iterator<Long> iterator = news.keys().asIterator();
            long id = 1;

            while(iterator.hasNext()){
                long tmpId = iterator.next();
                if(id < tmpId){
                    id = tmpId;
                }
            }
            newsEntity.setId(id+1L);
            tmpTitle += " " +(id+1);
            tmpText += " " +(id+1);

            newsEntity.setTitle(tmpTitle);
            newsEntity.setText(tmpText);
            news.put(id+1L, newsEntity);
        }
    }
    @Override
    public void update(Long id, NewsEntity newsEntity) {
        if(news.containsKey(id)){
            newsEntity.setId(id);
            news.put(id, newsEntity);
        }
    }

    @Override
    public void deleteById(long id) {
        news.remove(id);
    }
}
