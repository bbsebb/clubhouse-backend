package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.ClubCreate;
import fr.hoenheimsports.gamedomain.exception.ClubAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.spi.ClubRepository;

import java.util.Set;
@DomainService
public class ClubCreateImpl implements ClubCreate {
    private final ClubRepository clubRepository;

    public ClubCreateImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Club create(String code, String name, Set<Hall> halles) throws ClubAlreadyExistsException {
        if(this.clubRepository.findById(code).isPresent()) {
            throw new ClubAlreadyExistsException();
        }
        return this.clubRepository.save(new Club(code,name,halles));
    }

    @Override
    public Club create(String code, String name, Hall... halles) throws ClubAlreadyExistsException {
        return this.create(code,name,Set.of(halles));
    }
}
