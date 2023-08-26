package fr.hoenheimsports.bookdomain.spi.stub;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.Hall;
import fr.hoenheimsports.bookdomain.spi.HallRepository;

import java.util.*;

public class HallStub implements HallRepository {
    private final Map<UUID,Hall> halls;

    public HallStub() {
        this.halls = new HashMap<>();
        this.populate();
    }

    @Override
    public List<Hall> findAll() {
        return null;
    }

    @Override
    public Optional<Hall> findById(UUID id) {
        return Optional.ofNullable(this.halls.get(id));
    }

    private void populate() {
        Hall hall = new Hall(UUID.randomUUID(),"club house",new Address("rue des vosges",67800,"Hoeneim"),0);
        this.halls.put(hall.id(),hall);
    }
}
