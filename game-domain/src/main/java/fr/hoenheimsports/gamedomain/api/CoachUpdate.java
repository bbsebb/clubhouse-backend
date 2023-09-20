package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.CoachAlreadyExistsException;
import fr.hoenheimsports.gamedomain.exception.CoachNotFoundException;
import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.model.PhoneNumber;

public interface CoachUpdate {
    Coach update(String name, String phoneNumber) throws CoachNotFoundException;

    Coach update(String name, PhoneNumber phoneNumber) throws CoachNotFoundException;
}
