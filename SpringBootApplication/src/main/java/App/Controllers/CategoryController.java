package App.Controllers;


import App.Dto.CategoryDto;
import App.Services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

@RequestMapping("/categories")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public CategoryDto getCategoryById(@PathVariable long id) {
        return categoryService.getById(id);
    }

    @GetMapping
    public Collection<CategoryDto> getAllCategories() {
        return categoryService.getAll();
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        categoryService.create(categoryDto);
        return ResponseEntity
                .created(URI.create("/categories/" + categoryDto.getId()))
                .body(categoryDto);
    }

    @PutMapping("/{id}")
    public void updateCategory(@PathVariable long id, @RequestBody CategoryDto categoryDto) {
        categoryDto.setId(id);
        categoryService.update(categoryDto);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable long id) {
        categoryService.deleteById(id);
    }

}
