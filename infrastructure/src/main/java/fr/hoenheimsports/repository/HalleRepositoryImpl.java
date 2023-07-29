package fr.hoenheimsports.repository;

import fr.hoenheimsports.gamedomain.model.Halle;
import fr.hoenheimsports.gamedomain.spi.HalleRepository;
import fr.hoenheimsports.repository.entity.ClubEntityRepository;
import fr.hoenheimsports.repository.entity.HalleEntityRepository;
import fr.hoenheimsports.repository.entity.game.ClubEntity;
import fr.hoenheimsports.repository.entity.game.HalleEntity;
import fr.hoenheimsports.service.mapper.HalleMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class HalleRepositoryImpl implements HalleRepository {
    private final HalleEntityRepository halleEntityRepository;
    private final ClubEntityRepository clubEntityRepository;
    private final HalleMapper halleMapper;

    public HalleRepositoryImpl(HalleEntityRepository halleEntityRepositoryJPA, ClubEntityRepository clubEntityRepository, HalleMapper halleMapper) {
        this.halleEntityRepository = halleEntityRepositoryJPA;
        this.clubEntityRepository = clubEntityRepository;
        this.halleMapper = halleMapper;
    }

    @Override
    public Optional<Halle> findHallByKeys(String name, String address, int cp, String city) {
        Optional<HalleEntity>  optionalHalleEntity= this.halleEntityRepository.findByNameAndAddress_StreetAndAddress_PostalCodeAndAddress_City(name,address,cp,city);
        return optionalHalleEntity.map(this.halleMapper::halleEntityToHalle);
    }

    @Override
    public Set<Halle> findAllHalles() {
        return this.halleEntityRepository.findAll().stream().map(halleMapper::halleEntityToHalle).collect(Collectors.toSet());
    }

    @Override
    public Set<Halle> findByClubCode(String clubCode) {
         Optional<ClubEntity> optionalClubEntity =this.clubEntityRepository.findById(clubCode);
        return optionalClubEntity.map(ClubEntity::getHalles).orElse(new HashSet<>())
                .stream().map(halleMapper::halleEntityToHalle).collect(Collectors.toSet());
    }
}
