package fr.hoenheimsports.gamedomain.api;

import fr.hoenheimsports.gamedomain.model.Halle;

import java.util.Set;

public interface HalleDisplay {
    public Set<Halle> findAll();
    public Set<Halle> findByClubCode(String clubCode);
}
