package fr.hoenheimsports.dto.game.view;

import java.util.UUID;

public record TeamDTO(UUID id,
                      CategoryDTO category,
                      GenderDTO gender,
                      int number,
                      ClubDTO club,
                      TeamsColorDTO teamsColor,
                      CoachDTO coach) {
}
