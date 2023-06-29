package fr.hoenheimsports.dto.game;

import fr.hoenheimsports.gamedomain.model.TeamColor;

public record TeamsColorDTO(TeamColor shirtColor1,
                            TeamColor shirtColor2,
                            TeamColor goalkeeperColor1,
                            TeamColor goalkeeperColor2) {

}
