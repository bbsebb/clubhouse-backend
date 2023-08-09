package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.ClubDisplay;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.spi.ClubRepository;

import java.util.List;
import java.util.Optional;
@DomainService
public class ClubDisplayImpl implements ClubDisplay {

    private final ClubRepository clubRepository;

    public ClubDisplayImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }


    @Override
    public List<Club> findAll() {
        return this.clubRepository.findAllClub();
    }

    @Override
    public Optional<Club> findByCode(String code) {
        return this.clubRepository.findByCode(code);
    }
}
