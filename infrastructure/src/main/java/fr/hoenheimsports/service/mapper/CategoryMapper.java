package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.gamedomain.model.Category;
import fr.hoenheimsports.repository.entity.game.CategoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    public Category categoryEntityToCategory(CategoryEntity categoryEntity);
    public CategoryEntity categoryToCategoryEntity(Category category);
}
