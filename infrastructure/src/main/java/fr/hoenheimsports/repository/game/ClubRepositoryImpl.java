package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.spi.ClubRepository;
import fr.hoenheimsports.repository.game.entity.ClubEntityRepository;
import fr.hoenheimsports.service.game.mapper.ClubMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ClubRepositoryImpl implements ClubRepository {
    private final ClubEntityRepository clubEntityRepository;
    private final ClubMapper clubMapper;

    public ClubRepositoryImpl(ClubEntityRepository clubEntityRepository,
                              ClubMapper clubMapper) {
        this.clubEntityRepository = clubEntityRepository;
        this.clubMapper = clubMapper;
    }

    @Override
    public List<Club> findAll() {
        return this.clubEntityRepository.findAll().stream().map(clubMapper::clubEntityToClub).toList();
    }

    @Override
    public Optional<Club> findById(String code) {
        var opt = this.clubEntityRepository.findById(code);
        var bool = opt.isPresent();
        return this.clubEntityRepository.findById(code).map(clubMapper::clubEntityToClub);
    }

    @Override
    public Club save(Club club) {
        return this.clubMapper.clubEntityToClub(this.clubEntityRepository.save(this.clubMapper.clubToClubEntity(club)));
    }
}
