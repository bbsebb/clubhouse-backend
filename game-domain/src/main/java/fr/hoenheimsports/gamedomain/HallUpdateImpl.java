package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.HallUpdate;
import fr.hoenheimsports.gamedomain.exception.HallNotFoundException;
import fr.hoenheimsports.gamedomain.model.Address;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.spi.HallRepository;

import java.util.Optional;
@DomainService
public class HallUpdateImpl implements HallUpdate {
    private final HallRepository hallRepository;

    public HallUpdateImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Hall update(String name, Address address, GlueAuthorization glueAuthorization) throws HallNotFoundException {
        Optional<Hall> hall = this.hallRepository.findByKeys(name, address.street(), address.postalCode(), address.city());
        if(hall.isEmpty()) {
            throw new HallNotFoundException();
        }
        return this.hallRepository.save(new Hall(hall.get().id(),name, address, glueAuthorization));
    }
}
