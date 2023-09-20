package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Season;
import fr.hoenheimsports.gamedomain.spi.SeasonRepository;
import fr.hoenheimsports.repository.game.entity.SeasonEntityRepository;
import fr.hoenheimsports.service.game.mapper.SeasonMapper;
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

    @Override
    public Optional<Season> findById(String name) {
        return this.seasonEntityRepository.findById(name)
                .map(this.seasonMapper::seasonEntityToSeason);
    }

    @Override
    public Season save(Season season) {
        return this.seasonMapper.seasonEntityToSeason(this.seasonEntityRepository.save(this.seasonMapper.seasonToSeasonEntity(season)));
    }
}
