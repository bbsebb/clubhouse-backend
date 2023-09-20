package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.gamedomain.spi.CategoryRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Stub
public class CategoryRepositoryStub implements CategoryRepository {
    private final Map<String,Category> categoryMap= new HashMap<>();
    @Override
    public List<Category> findAll() {
        return this.categoryMap.values().stream().toList();
    }

    @Override
    public Optional<Category> findById(String name) {
        return Optional.ofNullable(this.categoryMap.get(name));
    }

    @Override
    public Category save(Category category) {
        this.categoryMap.put(category.name(),category);
        return category;
    }
}
