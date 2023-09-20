package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.HallAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Address;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.model.Hall;

public interface HallCreate {
    Hall create(String name, Address address, GlueAuthorization glueAuthorization) throws HallAlreadyExistsException;
}
