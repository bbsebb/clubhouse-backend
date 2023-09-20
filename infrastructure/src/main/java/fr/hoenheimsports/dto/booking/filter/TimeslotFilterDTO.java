package fr.hoenheimsports.dto.booking.filter;

import java.time.LocalDateTime;

public record TimeslotFilterDTO(LocalDateTime start, LocalDateTime end) {
}
