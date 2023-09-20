package fr.hoenheimsports.repository.game;

import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.spi.HallRepository;
import fr.hoenheimsports.repository.game.entity.ClubEntityRepository;
import fr.hoenheimsports.repository.game.entity.HallEntityRepository;
import fr.hoenheimsports.repository.game.entity.game.ClubEntity;
import fr.hoenheimsports.repository.game.entity.game.HallEntity;
import fr.hoenheimsports.service.game.mapper.HallMapper;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository("gameHallRepositoryImpl")
public class HallRepositoryImpl implements HallRepository {
    private final HallEntityRepository hallEntityRepository;
    private final ClubEntityRepository clubEntityRepository;
    private final HallMapper hallMapper;

    public HallRepositoryImpl(HallEntityRepository halleEntityRepositoryJPA, ClubEntityRepository clubEntityRepository, HallMapper halleMapper) {
        this.hallEntityRepository = halleEntityRepositoryJPA;
        this.clubEntityRepository = clubEntityRepository;
        this.hallMapper = halleMapper;
    }

    @Override
    public Optional<Hall> findByKeys(String name, String address, int cp, String city) {
        Optional<HallEntity>  optionalHalleEntity= this.hallEntityRepository.findByNameAndAddress_StreetAndAddress_PostalCodeAndAddress_City(name,address,cp,city);
        return optionalHalleEntity.map(this.hallMapper::hallEntityToHall);
    }

    @Override
    public Optional<Hall> findById(String id) {
        return  this.hallEntityRepository.findById(UUID.fromString(id)).map(this.hallMapper::hallEntityToHall);
    }

    @Override
    public Set<Hall> findAll() {
        return this.hallEntityRepository.findAll().stream().map(hallMapper::hallEntityToHall).collect(Collectors.toSet());
    }

    @Override
    public Set<Hall> findByClubId(String clubCode) {
         Optional<ClubEntity> optionalClubEntity =this.clubEntityRepository.findById(clubCode);
        return optionalClubEntity.map(ClubEntity::getHalls).orElse(new HashSet<>())
                .stream().map(hallMapper::hallEntityToHall).collect(Collectors.toSet());
    }

    @Override
    public Hall save(Hall hall) {
        return this.hallMapper.hallEntityToHall(this.hallEntityRepository.save(this.hallMapper.hallToHallEntity(hall)));
    }
}
