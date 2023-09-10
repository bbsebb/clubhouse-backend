package fr.hoenheimsports.service.booking;

import fr.hoenheimsports.bookdomain.api.HallUserCreate;
import fr.hoenheimsports.bookdomain.api.HallUserDisplay;
import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.dto.booking.HallUserCreateDTO;
import fr.hoenheimsports.dto.booking.HallUserDTO;
import fr.hoenheimsports.service.booking.mapper.HallUserMapper;
import fr.hoenheimsports.userdomain.api.UserDisplay;
import fr.hoenheimsports.userdomain.exception.UserNotFoundException;
import fr.hoenheimsports.userdomain.model.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HallUserServiceApplication {
    private final HallUserDisplay hallUserDisplay;
    private final HallUserCreate hallUserCreate;
    private final HallUserMapper hallUserMapper;
    private final UserDisplay userDisplay;

    public HallUserServiceApplication(HallUserDisplay userDisplay, HallUserCreate hallUserCreate, HallUserMapper hallUserMapper, UserDisplay userDisplay1) {
        this.hallUserDisplay = userDisplay;
        this.hallUserCreate = hallUserCreate;
        this.hallUserMapper = hallUserMapper;
        this.userDisplay = userDisplay1;
    }

    public HallUserDTO displayHallUser(String id) {
        return this.hallUserDisplay.findById(UUID.fromString(id))
                .map(this.hallUserMapper::toHallUserDTO)
                .orElseThrow();
    }

    public HallUserDTO createHallUserDTO(HallUserCreateDTO hallUserCreateDTO) {
        return this.hallUserMapper.toHallUserDTO(this.createHallUser(hallUserCreateDTO));
    }

    HallUser createHallUser(HallUserCreateDTO hallUserCreateDTO) {
        HallUser hallUser;
        if(hallUserCreateDTO.isRegistered()) {
            User user = this.userDisplay.findById(UUID.fromString(hallUserCreateDTO.id())).orElseThrow(UserNotFoundException::new);
            hallUser = this.hallUserCreate.createRegisteredUser(user.getId(),user.getUsername(),user.getEmail());
        } else {
            Address address = new Address(hallUserCreateDTO.address().street(), hallUserCreateDTO.address().cp(), hallUserCreateDTO.address().city());
            hallUser = this.hallUserCreate.createUnregisteredUser(hallUserCreateDTO.username(), hallUserCreateDTO.email(),address,false);
        }
        return hallUser;
    }
}
