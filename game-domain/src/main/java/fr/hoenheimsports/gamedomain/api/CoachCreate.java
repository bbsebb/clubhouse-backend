package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.exception.CoachAlreadyExistsException;
import fr.hoenheimsports.gamedomain.model.Coach;
import fr.hoenheimsports.gamedomain.model.PhoneNumber;

public interface CoachCreate {
    Coach create(String name, String phoneNumber) throws CoachAlreadyExistsException;

    Coach create(String name, PhoneNumber phoneNumber) throws CoachAlreadyExistsException;
}
