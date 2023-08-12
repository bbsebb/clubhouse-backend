package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.repository.user.entity.UserEntity;
import fr.hoenheimsports.userdomain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {
    public User userEntityToUser(UserEntity userEntity);
    public UserEntity userToUserEntity(User user);
}
