package fr.hoenheimsports.dto.game;

import java.util.UUID;

public record CoachDTO(UUID id, String name, PhoneNumberDTO phoneNumber) implements ContributorDTO {


}
