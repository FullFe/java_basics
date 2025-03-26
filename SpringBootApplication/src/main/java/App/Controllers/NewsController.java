package App.Controllers;

import App.NewsEntity;
import App.Services.NewsFeedService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsFeedService newsFeedService;

    public NewsController(NewsFeedService newsFeedService) {
        this.newsFeedService = newsFeedService;
    }

    @GetMapping("/{id}")
    public NewsEntity getNewsById(@PathVariable Long id){
        return newsFeedService.getById(id);
    }

    @GetMapping
    public Collection<NewsEntity> getAllNews(){
        return newsFeedService.getAll();
    }

    @PostMapping
    public void createNews(@RequestBody NewsEntity newsEntity){
        newsFeedService.create(newsEntity);
    }
    @PutMapping("/{id}")
    public void updateNews(@PathVariable Long id,@RequestBody NewsEntity newsEntity){
        newsFeedService.update(id, newsEntity);
    }
    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id){
        newsFeedService.deleteById(id);
    }
}
