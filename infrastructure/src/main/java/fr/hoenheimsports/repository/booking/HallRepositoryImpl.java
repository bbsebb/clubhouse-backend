package fr.hoenheimsports.repository.booking;

import fr.hoenheimsports.bookdomain.model.Hall;
import fr.hoenheimsports.bookdomain.spi.HallRepository;
import fr.hoenheimsports.repository.booking.entity.HallEntityRepository;
import fr.hoenheimsports.service.booking.mapper.BookingHallMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class HallRepositoryImpl implements HallRepository {
    private final HallEntityRepository hallEntityRepository;
    private final BookingHallMapper hallMapper;


    public HallRepositoryImpl(HallEntityRepository hallEntityRepository, BookingHallMapper hallMapper) {
        this.hallEntityRepository = hallEntityRepository;
        this.hallMapper = hallMapper;
    }

    @Override
    public List<Hall> findAll() {

        return this.hallEntityRepository.findAll().stream().map(this.hallMapper::toHall).toList();
    }

    @Override
    public Optional<Hall> findById(UUID id) {
        return this.hallEntityRepository.findById(id).map(this.hallMapper::toHall);
    }
}
