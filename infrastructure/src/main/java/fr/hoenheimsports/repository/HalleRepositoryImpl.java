package fr.hoenheimsports.repository;

import fr.hoenheimsports.gamedomain.model.Halle;
import fr.hoenheimsports.gamedomain.spi.HalleRepository;
import fr.hoenheimsports.repository.entity.HalleEntityRepository;
import fr.hoenheimsports.repository.entity.game.HalleEntity;
import fr.hoenheimsports.service.mapper.HalleMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class HalleRepositoryImpl implements HalleRepository {
    private final HalleEntityRepository halleEntityRepositoryJPA;
    private final HalleMapper halleMapper;

    public HalleRepositoryImpl(HalleEntityRepository halleEntityRepositoryJPA, HalleMapper halleMapper) {
        this.halleEntityRepositoryJPA = halleEntityRepositoryJPA;
        this.halleMapper = halleMapper;
    }

    @Override
    public Optional<Halle> findHallByKeys(String name, String address, int cp, String city) {
        Optional<HalleEntity>  optionalHalleEntity= this.halleEntityRepositoryJPA.findByNameAndAddress_StreetAndAddress_PostalCodeAndAddress_City(name,address,cp,city);
        return optionalHalleEntity.map(this.halleMapper::halleEntityToHalle);
    }
}
