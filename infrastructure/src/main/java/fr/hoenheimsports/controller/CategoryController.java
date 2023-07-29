package fr.hoenheimsports.controller;

import fr.hoenheimsports.dto.game.view.CategoryDTO;
import fr.hoenheimsports.service.CategoryServiceApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GameController.class);

    private final CategoryServiceApplication categoryServiceApplication;

    public CategoryController(CategoryServiceApplication categoryServiceApplication) {
        this.categoryServiceApplication = categoryServiceApplication;
    }

    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> displayCategories() {
        return ResponseEntity.ok(this.categoryServiceApplication.displayAll());
    }
}
