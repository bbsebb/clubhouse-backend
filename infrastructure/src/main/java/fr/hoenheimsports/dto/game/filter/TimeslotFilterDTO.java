package fr.hoenheimsports.dto.game.filter;

import java.time.LocalDateTime;

public record TimeslotFilterDTO(LocalDateTime start, LocalDateTime end) {
}
