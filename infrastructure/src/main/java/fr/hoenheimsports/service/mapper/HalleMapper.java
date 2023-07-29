package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.dto.game.view.HalleDTO;
import fr.hoenheimsports.gamedomain.model.Halle;
import fr.hoenheimsports.repository.entity.game.HalleEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HalleMapper {

    public Halle halleEntityToHalle(HalleEntity halleEntity);



    public HalleDTO halleToHalleDTO(Halle halle);

    public Halle halleDTOToHalle(HalleDTO halleDTO);
}
