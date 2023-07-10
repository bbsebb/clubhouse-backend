package fr.hoenheimsports.dto.game;

import java.time.LocalDate;
import java.time.LocalTime;

public record GameDTO(String code,
                      CompetitionDTO competition,
                      DayDTO day,
                      WeekDTO week,
                      SeasonDTO season,
                      HalleDTO halle,
                      RefereesDTO referees,
                      TeamDTO homeTeam,
                      TeamDTO visitingTeam,
                      ScoreDTO score,
                      FDMEDTO fdme,
                      LocalDate date,
                      LocalTime time) {
}
