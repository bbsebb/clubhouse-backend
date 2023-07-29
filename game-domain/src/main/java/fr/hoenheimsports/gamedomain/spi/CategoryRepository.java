package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Category;

import java.util.List;

public interface CategoryRepository {
    List<Category> findAll();
}
