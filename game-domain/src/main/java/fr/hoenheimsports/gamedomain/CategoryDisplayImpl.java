package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.CategoryDisplay;
import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.gamedomain.spi.CategoryRepository;

import java.util.List;
@DomainService
public class CategoryDisplayImpl implements CategoryDisplay {

    private final CategoryRepository categoryRepository;

    public CategoryDisplayImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return this.categoryRepository.findAll();
    }
}
