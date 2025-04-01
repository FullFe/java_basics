package App.Services;

import App.Dto.NewsDto;
import App.Entities.NewsEntity;
import App.Errors.CategoryNotFoundException;
import App.Errors.NewsNotFoundException;
import App.Repositories.CategoryRepository;
import App.Repositories.NewsRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Objects;

@RequiredArgsConstructor
@Slf4j
@Service
public class NewsFeedService implements CRUDService<NewsDto> {


    private final NewsRepository newsRepository;
    private final CategoryRepository categoryRepository;


    @Override
    public NewsDto getById(long id) {
        NewsEntity newsEntity = newsRepository.findById(id).orElseThrow(() -> new NewsNotFoundException(id));
        return mapToDto(newsEntity);
    }

    @Override
    public Collection<NewsDto> getAll() {
        return newsRepository.findAll().stream().map(NewsFeedService::mapToDto).toList();
    }

    @Override
    public void create(NewsDto newsDto) {
        NewsEntity newsEntity = mapToEntity(newsDto);
        boolean flag = categoryRepository.findAll().stream().anyMatch(item -> item.getCategoryName().equals(newsDto.getCategory()));
        if (flag) {
            newsEntity.setCategory(categoryRepository.findAll().stream()
                    .filter(item -> item.getCategoryName().equals(newsDto.getCategory())).findFirst().orElseThrow(CategoryNotFoundException::new));
            newsRepository.save(newsEntity);
        }else {
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public void update(NewsDto newsDto) {
       NewsEntity newsEntity = mapToEntity(newsDto);
        boolean flag = categoryRepository.findAll().stream().anyMatch(item -> item.getCategoryName().equals(newsDto.getCategory()));
        if (flag) {
            newsEntity.setCategory(categoryRepository.findAll().stream()
                    .filter(item -> item.getCategoryName().equals(newsDto.getCategory())).findFirst().orElseThrow(CategoryNotFoundException::new));
            newsEntity.setDate(Instant.now());
            newsRepository.save(newsEntity);
        }else {
            throw new CategoryNotFoundException();
        }
    }

    @Override
    public void deleteById(long id) {
        newsRepository.deleteById(id);
    }


    public static NewsDto mapToDto(NewsEntity newsEntity){
        NewsDto newsDto = new NewsDto();
        newsDto.setId(newsEntity.getId());
        newsDto.setTitle(newsEntity.getTitle());
        newsDto.setText(newsEntity.getText());
        newsDto.setDate(newsEntity.getDate());
        newsDto.setCategory(Objects.nonNull(newsEntity.getCategory())? newsEntity.getCategory().getCategoryName() : "");
        return newsDto;
    }

    public static NewsEntity mapToEntity(NewsDto newsDto){
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setId(newsDto.getId());
        newsEntity.setTitle(newsDto.getTitle());
        newsEntity.setText(newsDto.getText());
        newsEntity.setDate(newsDto.getDate());

        return newsEntity;
    }

}
