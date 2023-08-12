package fr.hoenheimsports.gamedomain.spi;

import fr.hoenheimsports.gamedomain.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    List<Category> findAll();
    Optional<Category> findByName(String name);
}
