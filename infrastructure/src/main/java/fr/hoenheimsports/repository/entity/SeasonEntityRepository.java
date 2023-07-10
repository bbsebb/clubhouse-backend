package fr.hoenheimsports.repository.entity;

import fr.hoenheimsports.repository.entity.game.SeasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SeasonEntityRepository extends JpaRepository<SeasonEntity, String> {
    @Query("SELECT s FROM SeasonEntity s WHERE :date BETWEEN s.startDate AND s.endDate")
    List<SeasonEntity> findSeasonsContainingDate(LocalDate date);
}