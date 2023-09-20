package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.spi.CoachRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Stub
public class CoachRepositoryStub implements CoachRepository {
    private final Map<UUID,Coach> coachs = new HashMap<>();
    @Override
    public Optional<Coach> findByKeys(String name) {
        return this.coachs.values().stream()
                .filter(coach -> coach.name().equals(name))
                .findFirst();
    }

    @Override
    public Optional<Coach> findById(UUID id) {
        return Optional.ofNullable(this.coachs.get(id));
    }

    @Override
    public Coach save(Coach coach) {
        this.coachs.put(coach.id(),coach);
        return coach;
    }
}
