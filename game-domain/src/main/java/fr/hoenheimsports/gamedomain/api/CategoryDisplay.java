package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Category;

import java.util.List;

public interface CategoryDisplay {
    List<Category> findAll();
}
