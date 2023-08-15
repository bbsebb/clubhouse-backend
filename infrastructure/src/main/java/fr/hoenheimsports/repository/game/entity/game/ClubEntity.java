package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
public class ClubEntity {
    @Id
    private String code;
    private String name;
    @ManyToMany
    private Set<HalleEntity> halles = new HashSet<>();

    public ClubEntity() {
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Set<HalleEntity> getHalles() {
        return halles;
    }

    public void setHalles(Set<HalleEntity> halles) {
        this.halles = halles;
    }


}