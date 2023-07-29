package fr.hoenheimsports.dto.game.view;

import java.util.UUID;

public record RefereeDTO(UUID id, String name) implements ContributorDTO {

}
