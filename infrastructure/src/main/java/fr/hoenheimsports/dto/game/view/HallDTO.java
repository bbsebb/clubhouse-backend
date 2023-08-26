package fr.hoenheimsports.dto.game.view;

import java.util.UUID;

public record HallDTO(UUID id, String name, AddressDTO address,
                      GlueAuthorizationDTO glueAuthorization) {

}
