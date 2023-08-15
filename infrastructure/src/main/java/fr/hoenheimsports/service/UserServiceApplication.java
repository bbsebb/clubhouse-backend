package fr.hoenheimsports.service;

import fr.hoenheimsports.dto.user.UserCreateDTO;
import fr.hoenheimsports.dto.user.UserDTO;
import fr.hoenheimsports.service.mapper.UserMapper;
import fr.hoenheimsports.userdomain.api.AccountService;
import fr.hoenheimsports.userdomain.api.UserCreate;
import fr.hoenheimsports.userdomain.api.UserDisplay;
import fr.hoenheimsports.userdomain.api.UserUpdate;
import fr.hoenheimsports.userdomain.exception.RoleNotFoundException;
import fr.hoenheimsports.userdomain.exception.UserAlreadyExistException;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
import fr.hoenheimsports.userdomain.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceApplication {
    private final UserCreate userCreate;
    private final UserUpdate userUpdate;
    private final UserDisplay userDisplay;
    private final AccountService accountService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceApplication(UserCreate userCreate,
                                  UserUpdate userUpdate, UserDisplay userDisplay, AccountService accountService, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userCreate = userCreate;
        this.userUpdate = userUpdate;
        this.userDisplay = userDisplay;
        this.accountService = accountService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO createUser(UserCreateDTO userCreateDTO) throws UserAlreadyExistException {
        return userMapper.userToUserDTO(this.userCreate.create(userCreateDTO.username(),this.passwordEncoder.encode(userCreateDTO.password()), userCreateDTO.email()));
    }

    public UserDTO addRole(String idUser, List<String> idsRole) throws UserNotFoundException, RoleNotFoundException {
        return userMapper.userToUserDTO(this.userUpdate.addRole(UUID.fromString(idUser), idsRole.stream().map(UUID::fromString).toArray(UUID[]::new)));
    }

    public List<UserDTO> displayUser() {
        return this.userDisplay.findAll().stream().map(userMapper::userToUserDTO).collect(Collectors.toList());
    }

    public UserDTO displayUser(String login) throws UserNotFoundException {
        return this.userMapper.userToUserDTO(this.accountService.loadByLogin(login));
    }
}
