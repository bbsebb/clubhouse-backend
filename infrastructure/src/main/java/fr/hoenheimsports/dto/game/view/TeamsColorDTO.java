package fr.hoenheimsports.dto.game.view;

import fr.hoenheimsports.gamedomain.model.TeamColor;

public record TeamsColorDTO(TeamColor shirtColor1,
                            TeamColor shirtColor2,
                            TeamColor goalkeeperColor1,
                            TeamColor goalkeeperColor2) {

}
