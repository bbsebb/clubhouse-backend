package fr.hoenheimsports.service.user.mapper;

import fr.hoenheimsports.dto.user.RoleDTO;
import fr.hoenheimsports.repository.user.entity.RoleEntity;
import fr.hoenheimsports.repository.user.entity.UserEntity;
import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RoleMapper {
    Role roleEntityToRole(RoleEntity roleEntity);
    RoleEntity roleToRoleEntity(Role role);


    RoleDTO roleToRoleDTO(Role role);

    Role roleDTOToRole(RoleDTO roleDTO);
}
