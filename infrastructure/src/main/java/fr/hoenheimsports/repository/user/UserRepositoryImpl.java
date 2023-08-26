package fr.hoenheimsports.repository.user;

import fr.hoenheimsports.repository.user.entity.UserEntityRepository;
import fr.hoenheimsports.service.user.mapper.UserMapper;
import fr.hoenheimsports.userdomain.model.User;
import fr.hoenheimsports.userdomain.spi.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

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

    @Override
    public User save(User user) {
        return this.userMapper.userEntityToUser(this.userEntityRepository.save(this.userMapper.userToUserEntity(user)));
    }

    @Override
    public Optional<User> findById(UUID userId) {
        return this.userEntityRepository.findById(userId).map(userMapper::userEntityToUser);
    }

    @Override
    public Set<User> findAll() {
        return this.userEntityRepository.findAll().stream().map(userMapper::userEntityToUser).collect(Collectors.toSet());
    }
}
