package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.dto.game.view.GenderDTO;
import fr.hoenheimsports.gamedomain.model.Gender;
import fr.hoenheimsports.repository.game.entity.game.GenderEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GenderMapper {
    GenderEntity mapGenderToGenderEntity(Gender gender);
    Gender mapGenderEntityToGender(GenderEntity genderEntity);

    Gender mapGenderDTOToGender(GenderDTO genderDTO);
}