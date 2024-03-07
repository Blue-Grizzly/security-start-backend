package dat3.database.api;

import dat3.database.dto.CategoryDto;
import dat3.database.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<String> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping(path ="/{id}")
    public CategoryDto getCategoryByName(@PathVariable String id) {
        return categoryService.getCategoryByName(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public CategoryDto addCategory(@RequestBody CategoryDto request) {
        return categoryService.addCategory(request);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping(path = "/{id}")
    public CategoryDto addCategory(@RequestBody CategoryDto request,@PathVariable int id) {
        return categoryService.editCategory(request,id);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }
}
