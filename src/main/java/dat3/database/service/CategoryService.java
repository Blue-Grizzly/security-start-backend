package dat3.database.service;

import dat3.database.dto.CategoryDto;
import dat3.database.entity.Category;
import dat3.database.repository.CategoryRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CategoryService {

    final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<String> getAllCategories() {
        List<Category> categories =  categoryRepository.findAll();
        //Convert from list of Categories to DTO-type, list of Strings
        return categories.stream().map((c)->new String(c.getName())).toList();
    }

    public CategoryDto getCategoryByName(String id) {
        Category category = categoryRepository.findByName(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        return new CategoryDto(category,false);
    }

    public CategoryDto addCategory(CategoryDto request) {
        if (request.getId() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot provide the id for a new category");
        }
        Category newCategory = new Category();
        updateCategory(newCategory, request);
        categoryRepository.save(newCategory);
        return new CategoryDto(newCategory,false);
    }

    private void updateCategory(Category original, CategoryDto r) {
        original.setName(r.getName());
    }

    public CategoryDto editCategory(CategoryDto request, int id) {
        if (request.getId() != id) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "You cannot change the id of an existing recipe");
        }
        Category categoryToEdit = categoryRepository.findById(id).orElseThrow(()
                -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        updateCategory(categoryToEdit,request);
        categoryRepository.save(categoryToEdit);
        return new CategoryDto(categoryToEdit,false);
    }

    public ResponseEntity deleteCategory(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
        categoryRepository.delete(category);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}

