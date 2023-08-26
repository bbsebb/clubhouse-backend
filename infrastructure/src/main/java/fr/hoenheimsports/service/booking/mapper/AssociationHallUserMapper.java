package fr.hoenheimsports.service.booking.mapper;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.dto.booking.HallUserDTO;
import fr.hoenheimsports.repository.booking.entity.booking.AssociationHallUserEntity;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface AssociationHallUserMapper {
    AssociationHallUserEntity toAssociationHallUserEntity(AssociationHallUser associationHallUser);
    AssociationHallUser toAssociationHallUser(AssociationHallUserEntity associationHallUserEntity);

    HallUserDTO toAssociationHallUserDTO(AssociationHallUser associationHallUser);

}

