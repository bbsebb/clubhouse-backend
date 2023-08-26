package fr.hoenheimsports.service.booking;

import fr.hoenheimsports.bookdomain.api.HallDisplay;
import fr.hoenheimsports.dto.booking.HallDTO;
import fr.hoenheimsports.service.booking.mapper.BookingHallMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service("bookingHallServiceApplication")
public class HallServiceApplication {
    private final HallDisplay hallDisplay;
    private final BookingHallMapper hallMapper;

    public HallServiceApplication(HallDisplay hallDisplay, BookingHallMapper hallMapper) {
        this.hallDisplay = hallDisplay;
        this.hallMapper = hallMapper;
    }

    public List<HallDTO> displayHalls() {
        return this.hallDisplay.findAll().stream().map(this.hallMapper::toHallDTO).toList();
    }

    public HallDTO displayHall(String id) {
        return this.hallDisplay.findById(UUID.fromString(id))
                .map(this.hallMapper::toHallDTO)
                .orElseThrow();
    }
}
