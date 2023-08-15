package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.dto.user.RoleDTO;
import fr.hoenheimsports.dto.user.UserDTO;
import fr.hoenheimsports.repository.user.entity.UserEntity;
import fr.hoenheimsports.userdomain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    User userEntityToUser(UserEntity userEntity);
    UserEntity userToUserEntity(User user);

    User userToUserDTO (UserDTO userDTO);
    UserDTO userToUserDTO(User user);
}
