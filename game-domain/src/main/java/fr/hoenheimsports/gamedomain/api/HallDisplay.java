package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Hall;

import java.util.Set;

public interface HallDisplay {
    public Set<Hall> findAll();
    public Set<Hall> findByClubCode(String clubCode);
}
