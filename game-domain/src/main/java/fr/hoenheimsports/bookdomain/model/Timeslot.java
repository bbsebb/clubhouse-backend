package fr.hoenheimsports.bookdomain.model;

import java.time.LocalDateTime;

public record Timeslot(LocalDateTime start,LocalDateTime end) {
}
