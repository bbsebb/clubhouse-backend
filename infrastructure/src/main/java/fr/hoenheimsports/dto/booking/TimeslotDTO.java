package fr.hoenheimsports.dto.booking;

import java.time.LocalDateTime;

public record TimeslotDTO(LocalDateTime start, LocalDateTime end) {
}
