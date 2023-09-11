package fr.hoenheimsports.service.user;

import fr.hoenheimsports.dto.user.UserCreateDTO;
import fr.hoenheimsports.dto.user.UserDTO;
import fr.hoenheimsports.service.user.mapper.UserMapper;
import fr.hoenheimsports.userdomain.api.*;
import fr.hoenheimsports.userdomain.exception.RoleNotFoundException;
import fr.hoenheimsports.userdomain.exception.UserAlreadyExistException;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
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
    private final RoleDisplay roleDisplay;

    public UserServiceApplication(UserCreate userCreate,
                                  UserUpdate userUpdate, UserDisplay userDisplay, AccountService accountService, UserMapper userMapper, PasswordEncoder passwordEncoder, RoleDisplay roleDisplay) {
        this.userCreate = userCreate;
        this.userUpdate = userUpdate;
        this.userDisplay = userDisplay;
        this.accountService = accountService;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleDisplay = roleDisplay;
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

    public UserDTO activateUser(String id) {
        return this.addRole(id, List.of(this.roleDisplay.findAll().stream().filter(role -> role.getRoleName().equals("USER")).findFirst().get().getId().toString()));
    }
}
