package fr.hoenheimsports.repository;

import fr.hoenheimsports.gamedomain.model.Season;
import fr.hoenheimsports.gamedomain.spi.SeasonRepository;
import fr.hoenheimsports.repository.entity.SeasonEntityRepository;
import fr.hoenheimsports.service.mapper.SeasonMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public class SeasonRepositoryImpl implements SeasonRepository {
    private final SeasonEntityRepository seasonEntityRepository;
    private final SeasonMapper seasonMapper;

    public SeasonRepositoryImpl(SeasonEntityRepository seasonEntityRepository, SeasonMapper seasonMapper) {
        this.seasonEntityRepository = seasonEntityRepository;
        this.seasonMapper = seasonMapper;
    }

    @Override
    public Optional<Season> findByDate(LocalDate localDate) {
        return this.seasonEntityRepository
                .findSeasonsContainingDate(localDate)
                .stream()
                .map(this.seasonMapper::seasonEntityToSeason)
                .findFirst();
    }
}
