package fr.hoenheimsports.controller.booking;

import fr.hoenheimsports.dto.booking.BookingCreateDTO;
import fr.hoenheimsports.dto.booking.BookingDTO;
import fr.hoenheimsports.dto.booking.BookingPayDTO;
import fr.hoenheimsports.service.booking.BookingServiceApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    private final BookingServiceApplication bookingServiceApplication;

    public BookingController(BookingServiceApplication bookingServiceApplication) {
        this.bookingServiceApplication = bookingServiceApplication;
    }

    @GetMapping("")
    public ResponseEntity<List<BookingDTO>> displayBookings() {
        return ResponseEntity.ok(this.bookingServiceApplication.displayBookings());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingDTO> displayBooking(@PathVariable String id) {
        return ResponseEntity.ok(this.bookingServiceApplication.displayBooking(id));
    }

    @PostMapping("/create")
    public ResponseEntity<BookingDTO> createBooking(@RequestBody BookingCreateDTO bookingCreateDTO) {
        return ResponseEntity.ok(this.bookingServiceApplication.createBooking(bookingCreateDTO));
    }

    @PutMapping("/{id}/pay")
    public ResponseEntity<BookingDTO> payBooking(@PathVariable String id,@RequestBody BookingPayDTO bookingPayDTO){
        return ResponseEntity.ok(this.bookingServiceApplication.payBooking(id,bookingPayDTO));
    }

    @PutMapping("/{id}/accept")
    public ResponseEntity<BookingDTO> acceptBooking(@PathVariable String id){
        return ResponseEntity.ok(this.bookingServiceApplication.acceptBooking(id));
    }
    @PutMapping("/{id}/refuse")
    public ResponseEntity<BookingDTO> refuseBooking(@PathVariable String id){
        return ResponseEntity.ok(this.bookingServiceApplication.refuseBooking(id));
    }
    @PutMapping("/{id}/cancel")
    public ResponseEntity<BookingDTO> cancelBooking(@PathVariable String id){
        return ResponseEntity.ok(this.bookingServiceApplication.cancelBooking(id));
    }
    @PutMapping("/{id}/valid")
    public ResponseEntity<BookingDTO> validBooking(@PathVariable String id){
        return ResponseEntity.ok(this.bookingServiceApplication.validBooking(id));
    }
}
