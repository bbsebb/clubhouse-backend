package fr.hoenheimsports.gamedomain;

import fr.hoenheimsports.gamedomain.annotation.DomainService;
import fr.hoenheimsports.gamedomain.api.HallCreate;
import fr.hoenheimsports.gamedomain.exception.HallAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Address;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.spi.HallRepository;

import java.util.UUID;
@DomainService
public class HallCreateImpl implements HallCreate {
    private final HallRepository hallRepository;

    public HallCreateImpl(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Override
    public Hall create(String name, Address address, GlueAuthorization glueAuthorization) throws HallAlreadyExistsException {
        if(this.hallRepository.findByKeys(name,address.street(),address.postalCode(),address.city()).isPresent()) {
            throw new HallAlreadyExistsException();
        }
        return this.hallRepository.save(new Hall(UUID.randomUUID(),name, address, glueAuthorization));
    }
}
