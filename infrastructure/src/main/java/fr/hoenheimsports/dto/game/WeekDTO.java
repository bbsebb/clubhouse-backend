package fr.hoenheimsports.dto.game;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Locale;

public record WeekDTO(int year, int week) { }
