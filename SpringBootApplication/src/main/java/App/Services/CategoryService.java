package App.Services;

import App.Dto.CategoryDto;
import App.Dto.NewsDto;
import App.Entities.CategoryEntity;
import App.Entities.NewsEntity;
import App.Errors.CategoryNotFoundException;
import App.Errors.CloneCategoryException;
import App.Repositories.CategoryRepository;

import App.Repositories.NewsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService implements CRUDService<CategoryDto>{

    private final CategoryRepository categoryRepository;
    private final NewsRepository newsRepository;


    @Override
    public CategoryDto getById(long id) {

        return mapToDto(categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id)));
    }

    @Override
    public Collection<CategoryDto> getAll() {
        return categoryRepository.findAll().stream().map(CategoryService::mapToDto).toList();
    }

    @Override
    public void create(CategoryDto o) {
        CategoryEntity categoryEntity = mapToEntity(o);
        categoryRepository.findAll().forEach(item -> {
            if (item.getCategoryName().equals(categoryEntity.getCategoryName())) {
                throw new CloneCategoryException(item.getCategoryName());
            }
        });
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void update(CategoryDto o) {
        CategoryEntity categoryEntity = mapToEntity(o);
        long oldId = o.getId();
        List<NewsEntity> relationNews = categoryRepository.findById(oldId).orElseThrow(CategoryNotFoundException::new).getNews();
        categoryEntity.setNews(relationNews);
        for (NewsEntity newsEntity : relationNews) {
            newsEntity.setCategory(categoryEntity);
            newsRepository.save(newsEntity);
        }

        categoryEntity.setNews(relationNews);
        categoryRepository.save(categoryEntity);
    }

    @Override
    public void deleteById(long id) {
        categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepository.deleteById(id);
    }

    public static CategoryEntity mapToEntity(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(categoryDto.getId());
        categoryEntity.setCategoryName(categoryDto.getCategoryName());

        List<NewsDto> tmp = categoryDto.getNews();

        categoryEntity.setNews(tmp.stream().map(NewsFeedService::mapToEntity).toList());
        return categoryEntity;
    }

    public static CategoryDto mapToDto(CategoryEntity category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setCategoryName(category.getCategoryName());

        List<NewsEntity> tmp = category.getNews();

        categoryDto.setNews(tmp.stream().map(NewsFeedService::mapToDto).toList());
        return categoryDto;
    }
}
