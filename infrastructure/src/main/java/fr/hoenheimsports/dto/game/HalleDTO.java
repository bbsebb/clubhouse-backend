package fr.hoenheimsports.dto.game;

import java.util.UUID;

public record HalleDTO(UUID id, String name, AddressDTO address,
                       GlueAuthorizationDTO glueAuthorization) {

}
