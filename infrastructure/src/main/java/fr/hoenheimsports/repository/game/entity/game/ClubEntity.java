package fr.hoenheimsports.repository.game.entity.game;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
@Entity
public class ClubEntity {
    @Id
    private String code;
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<HallEntity> halls = new HashSet<>();

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
    public Set<HallEntity> getHalls() {
        return halls;
    }

    public void setHalls(Set<HallEntity> halls) {
        this.halls = halls;
    }


}
