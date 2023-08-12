package fr.hoenheimsports.repository.user;

import fr.hoenheimsports.repository.user.entity.UserEntityRepository;
import fr.hoenheimsports.service.mapper.UserMapper;
import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public class UserRepositoryImpl implements UserRepository {
    private final UserEntityRepository userEntityRepository;
    private final UserMapper userMapper;

    public UserRepositoryImpl(UserEntityRepository userEntityRepository, UserMapper userMapper) {
        this.userEntityRepository = userEntityRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return this.userEntityRepository.findByUsername(username).map(this.userMapper::userEntityToUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return this.userEntityRepository.findByEmail(email).map(this.userMapper::userEntityToUser);
    }
}
