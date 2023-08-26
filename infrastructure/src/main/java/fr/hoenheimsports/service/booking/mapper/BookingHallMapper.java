package fr.hoenheimsports.service.booking.mapper;

import fr.hoenheimsports.bookdomain.model.Hall;
import fr.hoenheimsports.dto.booking.HallDTO;
import fr.hoenheimsports.repository.booking.entity.booking.HallEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookingHallMapper {
    public Hall toHall(HallEntity hallEntity);
    public HallEntity toHallEntity(Hall hall);
    public HallDTO toHallDTO(Hall hall);
}


