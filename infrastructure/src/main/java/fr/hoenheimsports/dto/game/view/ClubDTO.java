package fr.hoenheimsports.dto.game.view;

import java.util.Set;

public record ClubDTO(String code, String name, Set<HalleDTO> halles) {

}
