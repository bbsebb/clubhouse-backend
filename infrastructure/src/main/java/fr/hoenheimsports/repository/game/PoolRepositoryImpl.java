package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Pool;
import fr.hoenheimsports.gamedomain.spi.PoolRepository;
import fr.hoenheimsports.repository.game.entity.PoolEntityRepository;
import fr.hoenheimsports.service.game.mapper.PoolMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class PoolRepositoryImpl implements PoolRepository {
    private final PoolEntityRepository poolEntityRepository;
    private final PoolMapper poolMapper;

    public PoolRepositoryImpl(PoolEntityRepository poolEntityRepository, PoolMapper poolMapper) {
        this.poolEntityRepository = poolEntityRepository;
        this.poolMapper = poolMapper;
    }

    @Override
    public Pool save(Pool pool) {
        return this.poolMapper.poolEntityToPool(this.poolEntityRepository.save(this.poolMapper.poolToPoolEntity(pool)));
    }

    @Override
    public Optional<Pool> findById(String code) {
        return this.poolEntityRepository.findById(code).map(this.poolMapper::poolEntityToPool);
    }
}
