package fr.hoenheimsports.repository.game.entity;

import fr.hoenheimsports.repository.game.entity.game.HallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository("gameHallEntityRepository")
public interface HallEntityRepository extends JpaRepository<HallEntity, UUID> {
    Optional<HallEntity> findByNameAndAddress_StreetAndAddress_PostalCodeAndAddress_City(String name, String street, int postalCode, String city);

}