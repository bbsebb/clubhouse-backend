package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.spi.ClubRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Stub
public class ClubRepositoryStub implements ClubRepository {
    private final Map<String,Club> clubs = new HashMap<>();

    @Override
    public List<Club> findAll() {
        return this.clubs.values().stream().toList();
    }

    @Override
    public Optional<Club> findById(String code) {
        return Optional.ofNullable(this.clubs.get(code));
    }

    @Override
    public Club save(Club club) {
        this.clubs.put(club.code(),club);
        return club;
    }
}
