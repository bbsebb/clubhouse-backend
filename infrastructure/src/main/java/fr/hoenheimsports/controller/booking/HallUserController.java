package fr.hoenheimsports.controller.booking;

import fr.hoenheimsports.dto.booking.HallUserCreateDTO;
import fr.hoenheimsports.dto.booking.HallUserDTO;
import fr.hoenheimsports.service.booking.HallUserServiceApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hall-users")
public class HallUserController {
    private final HallUserServiceApplication userServiceApplication;

    public HallUserController(HallUserServiceApplication userServiceApplication) {
        this.userServiceApplication = userServiceApplication;
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallUserDTO> displayHallUser(@PathVariable String id) {
        return ResponseEntity.ok(this.userServiceApplication.displayHallUser(id));
    }

    @PostMapping("/create")
    public ResponseEntity<HallUserDTO> create(@RequestBody HallUserCreateDTO hallUserCreateDTO) {
        return ResponseEntity.ok(this.userServiceApplication.createHallUserDTO(hallUserCreateDTO));
    }
}
