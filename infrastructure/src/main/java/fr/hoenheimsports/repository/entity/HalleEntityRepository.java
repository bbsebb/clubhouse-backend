package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.HalleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HalleEntityRepository extends JpaRepository<HalleEntity, UUID> {
    Optional<HalleEntity> findByNameAndAddress_StreetAndAddress_PostalCodeAndAddress_City(String name, String street,int postalCode,String city);

}