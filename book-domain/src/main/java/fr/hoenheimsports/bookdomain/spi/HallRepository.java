package fr.hoenheimsports.bookdomain.spi;

import fr.hoenheimsports.bookdomain.model.Hall;

import java.util.List;

public interface HallRepository {
    List<Hall> findAll();
}
