package fr.hoenheimsports.service;

import fr.hoenheimsports.dto.game.view.CategoryDTO;
import fr.hoenheimsports.gamedomain.api.CategoryDisplay;
import fr.hoenheimsports.service.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceApplication {
    private final CategoryDisplay categoryDisplay;
    private final CategoryMapper categoryMapper;


    public CategoryServiceApplication(CategoryDisplay categoryDisplay, CategoryMapper categoryMapper) {
        this.categoryDisplay = categoryDisplay;
        this.categoryMapper = categoryMapper;
    }

    public List<CategoryDTO> displayAll() {
        return this.categoryDisplay.findAll().stream().map(categoryMapper::categoryToCategoryDTO).collect(Collectors.toList());
    }
}
