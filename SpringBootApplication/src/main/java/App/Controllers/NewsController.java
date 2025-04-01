package App.Controllers;

import App.Dto.NewsDto;
import App.Services.NewsFeedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/news")
public class NewsController {
    private final NewsFeedService newsFeedService;

    public NewsController(NewsFeedService newsFeedService) {
        this.newsFeedService = newsFeedService;
    }

    @GetMapping("/{id}")
    public NewsDto getNewsById(@PathVariable Long id){
        return newsFeedService.getById(id);
    }

    @GetMapping
    public Collection<NewsDto> getAllNews(){
        return newsFeedService.getAll();
    }

    @PostMapping
    public ResponseEntity<NewsDto> createNews(@RequestBody NewsDto newsEntity){
        newsFeedService.create(newsEntity);
        return ResponseEntity
                .created(URI.create("/news/" + newsEntity.getId()))
                .body(newsEntity);

    }
    @PutMapping("/{id}")
    public void updateNews(@PathVariable long id, @RequestBody NewsDto newsEntity){
        newsEntity.setId(id);
        newsFeedService.update(newsEntity);
    }
    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id){
        newsFeedService.deleteById(id);
    }

}
