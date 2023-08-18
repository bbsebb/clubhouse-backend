package fr.hoenheimsports.bookdomain.model;

import java.util.UUID;

public interface HallUser {
    UUID id();
    String username();
    String email();

}
