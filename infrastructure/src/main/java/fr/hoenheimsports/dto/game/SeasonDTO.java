package fr.hoenheimsports.dto.game;

import java.time.LocalDate;

public record SeasonDTO(String name, LocalDate startDate, LocalDate endDate) {

}
