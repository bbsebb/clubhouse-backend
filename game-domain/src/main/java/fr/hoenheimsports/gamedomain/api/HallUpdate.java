package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.HallNotFoundException;
import fr.hoenheimsports.gamedomain.model.Address;
import fr.hoenheimsports.gamedomain.model.GlueAuthorization;
import fr.hoenheimsports.gamedomain.model.Hall;

public interface HallUpdate {
     Hall update(String name, Address address, GlueAuthorization glueAuthorization) throws HallNotFoundException;
}
