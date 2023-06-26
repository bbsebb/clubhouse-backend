package fr.hoenheimsports.service.mapper;

import fr.hoenheimsports.gamedomain.model.Halle;
import fr.hoenheimsports.repository.entity.game.HalleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HalleMapper {
    @Mapping(source = "id", target = "id")
    public Halle halleEntityToHalle(HalleEntity halleEntity);
    @Mapping(source = "id", target = "id")
    public HalleEntity halleToHalleEntity(Halle halle);
}
