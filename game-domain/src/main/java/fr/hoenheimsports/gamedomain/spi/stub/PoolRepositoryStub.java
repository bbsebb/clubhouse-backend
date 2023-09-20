package fr.hoenheimsports.gamedomain.spi.stub;

import fr.hoenheimsports.gamedomain.annotation.Stub;
import fr.hoenheimsports.gamedomain.model.Pool;
import fr.hoenheimsports.gamedomain.spi.PoolRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Stub
public class PoolRepositoryStub implements PoolRepository {
    private final Map<String,Pool> pools = new HashMap<>();
    @Override
    public Pool save(Pool pool) {
        this.pools.put(pool.code(),pool);
        return pool;
    }

    @Override
    public Optional<Pool> findById(String code) {
        return Optional.ofNullable(this.pools.get(code));
    }
}
