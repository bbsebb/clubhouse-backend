package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.spi.HallRepository;

import java.util.*;

@Stub
public class HallRepositoryStub implements HallRepository {
    private final Map<UUID,Hall> halls = new HashMap<>();
    private final ClubRepositoryStub clubRepositoryStub;

    public HallRepositoryStub(ClubRepositoryStub clubRepositoryStub) {
        this.clubRepositoryStub = clubRepositoryStub;
    }

    @Override
    public Optional<Hall> findByKeys(String name, String address, int cp, String city) {
        return this.halls.values().stream()
                .filter(hall -> hall.name().equals(name) && hall.address().street().equals(address) && hall.address().postalCode() == cp && hall.address().city().equals(city))
                .findFirst();
    }

    @Override
    public Optional<Hall> findById(String id) {
        return Optional.ofNullable(this.halls.get(UUID.fromString(id)));
    }

    @Override
    public Set<Hall> findAll() {
        return new HashSet<>(this.halls.values());
    }

    @Override
    public Set<Hall> findByClubId(String clubId) {
        return this.clubRepositoryStub.findById(clubId).map(Club::halls).orElse(new HashSet<>());
    }

    @Override
    public Hall save(Hall hall) {
        this.halls.put(hall.id(),hall);
        return hall;
    }
}
