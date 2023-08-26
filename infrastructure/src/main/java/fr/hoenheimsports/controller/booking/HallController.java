package fr.hoenheimsports.controller.booking;

import fr.hoenheimsports.dto.booking.HallDTO;
import fr.hoenheimsports.service.booking.HallServiceApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("bookingHallController")
@RequestMapping("/api/booking-halls")
public class HallController {
    private final HallServiceApplication hallServiceApplication;

    public HallController(HallServiceApplication hallServiceApplication) {
        this.hallServiceApplication = hallServiceApplication;
    }

    @GetMapping("")
    public ResponseEntity<List<HallDTO>> displayHalls() {
        return ResponseEntity.ok(this.hallServiceApplication.displayHalls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HallDTO> displayHall(@PathVariable String id) {
        return ResponseEntity.ok(this.hallServiceApplication.displayHall(id));
    }
}
