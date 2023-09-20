package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Referee;
import fr.hoenheimsports.gamedomain.spi.RefereeRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
@Stub
public class RefereeRepositoryStub implements RefereeRepository {
    private final Map<UUID,Referee> referees = new HashMap<>();
    @Override
    public Optional<Referee> findByKeys(String name) {
        return this.referees.values().stream().filter(referee -> referee.name().equals(name)).findFirst();
    }

    @Override
    public Optional<Referee> findById(UUID id) {
        return Optional.ofNullable(this.referees.get(id));
    }

    @Override
    public Referee save(Referee referee) {
        this.referees.put(referee.id(),referee);
        return referee;
    }
}
