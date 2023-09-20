package fr.hoenheimsports.dto.game.view;

import java.time.LocalDateTime;

public record GameDTO(String code,
                      PoolDTO pool,
                      DayDTO day,
                      WeekDTO week,
                      SeasonDTO season,
                      HallDTO halle,
                      RefereesDTO referees,
                      TeamDTO homeTeam,
                      TeamDTO visitingTeam,
                      ScoreDTO score,
                      FDMEDTO fdme,
                      LocalDateTime dateTime,
                      boolean isPlayed) {
}
