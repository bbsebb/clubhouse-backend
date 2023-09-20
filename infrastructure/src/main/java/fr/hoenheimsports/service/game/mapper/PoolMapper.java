package fr.hoenheimsports.service.game.mapper;

import fr.hoenheimsports.dto.game.view.HallDTO;
import fr.hoenheimsports.gamedomain.model.Hall;
import fr.hoenheimsports.gamedomain.model.Pool;
import fr.hoenheimsports.repository.game.entity.game.HallEntity;
import fr.hoenheimsports.repository.game.entity.game.PoolEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PoolMapper {
    public Pool poolEntityToPool(PoolEntity poolEntity);

    PoolEntity poolToPoolEntity(Pool pool);
}
