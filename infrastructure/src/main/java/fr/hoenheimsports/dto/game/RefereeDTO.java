package fr.hoenheimsports.dto.game;

import java.util.UUID;

public record RefereeDTO(UUID id, String name) implements ContributorDTO {

}
