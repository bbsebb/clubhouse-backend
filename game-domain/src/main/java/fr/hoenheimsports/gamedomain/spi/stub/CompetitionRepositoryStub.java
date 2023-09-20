package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Competition;
import fr.hoenheimsports.gamedomain.spi.CompetitionRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@Stub
public class CompetitionRepositoryStub implements CompetitionRepository {
    private final Map<String,Competition> competitions = new HashMap<>();
    @Override
    public Competition save(Competition competition) {
        this.competitions.put(competition.name(),competition);
        return competition;
    }

    @Override
    public Optional<Competition> findById(String name) {
        return Optional.ofNullable(this.competitions.get(name));
    }
}
