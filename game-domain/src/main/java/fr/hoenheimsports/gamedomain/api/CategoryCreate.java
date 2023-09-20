package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Category;

public interface CategoryCreate {
    Category create(int age, boolean isMaxAge)  ;
}
