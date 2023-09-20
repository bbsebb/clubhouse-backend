package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.ClubUpdate;
import fr.hoenheimsports.gamedomain.exception.ClubNotFoundException;
import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.spi.ClubRepository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@DomainService
public class ClubUpdateImpl implements ClubUpdate {
    private final ClubRepository clubRepository;

    public ClubUpdateImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public Club update(String code, String name, Set<Hall> halls) throws ClubNotFoundException {
        Optional<Club> club = this.clubRepository.findById(code);
        if(club.isEmpty()){
            throw new ClubNotFoundException();
        }
       Set<Hall> newHalls = new HashSet<>(club.get().halls());
        newHalls.addAll(halls);
        return this.clubRepository.save(new Club(code,name,newHalls));
    }

    @Override
    public Club update(String code, String name, Hall... halles) throws ClubNotFoundException {
        return this.update(code,name,Set.of(halles));
    }
}
