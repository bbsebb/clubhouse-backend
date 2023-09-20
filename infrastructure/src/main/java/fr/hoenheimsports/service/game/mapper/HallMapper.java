package fr.hoenheimsports.service.game.mapper;

import fr.hoenheimsports.dto.game.view.HallDTO;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.repository.game.entity.game.HallEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HallMapper {

    public Hall hallEntityToHall(HallEntity hallEntity);



    public HallDTO hallToHalleDTO(Hall hall);


    HallEntity hallToHallEntity(Hall hall);
}
