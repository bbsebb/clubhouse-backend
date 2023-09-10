package fr.hoenheimsports.bookdomain.spi.stub;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.Hall;
import fr.hoenheimsports.bookdomain.spi.HallRepository;

import java.util.*;

public class HallStub implements HallRepository {
    private final Map<UUID, Hall> halls;

    public HallStub() {
        this.halls = new HashMap<>();
    }

    @Override
    public List<Hall> findAll() {
        return this.halls.values().stream().toList();
    }

    @Override
    public Optional<Hall> findById(UUID id) {
        return Optional.ofNullable(this.halls.get(id));
    }

    public Map<UUID, Hall> getHalls() {
        return halls;
    }
}
