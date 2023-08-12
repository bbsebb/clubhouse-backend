package fr.hoenheimsports.dto.game.create;

import fr.hoenheimsports.dto.game.view.GenderDTO;

import java.time.LocalDateTime;

public record GameCreateDTO(String categoryId, GenderDTO gender, LocalDateTime dateTime, String halleId, String homeTeamClubCode, String visitingTeamClubCode ) {
}
