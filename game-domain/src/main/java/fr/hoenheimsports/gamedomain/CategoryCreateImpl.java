package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.CategoryCreate;
import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.gamedomain.spi.CategoryRepository;

@DomainService
public class CategoryCreateImpl implements CategoryCreate {
    private final CategoryRepository categoryRepository;

    public CategoryCreateImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category create(int age, boolean isMaxAge)   {
        String name = determineName(age,isMaxAge);
        return this.categoryRepository.findById(name).orElseGet(() -> this.categoryRepository.save(new Category(name,age,isMaxAge)));
    }

    private String determineName(int age, boolean isMaxAge) {
        if(isMaxAge) {
            return "-"+age+" ans";
        } else {
            return "senior";
        }
    }
}
