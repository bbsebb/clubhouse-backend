package fr.hoenheimsports.dto.game.view;

import java.time.LocalDateTime;

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
                      LocalDateTime dateTime,
                      boolean isPlayed) {
}
