package fr.hoenheimsports.repository;

import fr.hoenheimsports.gamedomain.model.Club;
import fr.hoenheimsports.gamedomain.spi.ClubRepository;
import fr.hoenheimsports.repository.entity.ClubEntityRepository;
import fr.hoenheimsports.service.mapper.ClubMapper;
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
    public List<Club> findAllClub() {
        return this.clubEntityRepository.findAll().stream().map(clubMapper::clubEntityToClub).toList();
    }

    @Override
    public Optional<Club> findByCode(String code) {
        return this.clubEntityRepository.findById(code).map(clubMapper::clubEntityToClub);
    }
}
