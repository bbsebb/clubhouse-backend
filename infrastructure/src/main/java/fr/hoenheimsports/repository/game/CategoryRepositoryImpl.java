package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.gamedomain.spi.CategoryRepository;
import fr.hoenheimsports.repository.game.entity.CategoryEntityRepository;
import fr.hoenheimsports.service.mapper.CategoryMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    private final CategoryEntityRepository categoryEntityRepository;
    private final CategoryMapper categoryMapper;

    public CategoryRepositoryImpl(CategoryEntityRepository categoryEntityRepository,
                                  CategoryMapper categoryMapper) {
        this.categoryEntityRepository = categoryEntityRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> findAll() {
        Sort sort = Sort.by(Sort.Direction.ASC,"isMaxAge","age");
        return this.categoryEntityRepository.findAll(sort).stream().map(categoryMapper::categoryEntityToCategory).collect(Collectors.toList());
    }

    @Override
    public Optional<Category> findByName(String name) {
        return this.categoryEntityRepository.findById(name).map(this.categoryMapper::categoryEntityToCategory);
    }
}
