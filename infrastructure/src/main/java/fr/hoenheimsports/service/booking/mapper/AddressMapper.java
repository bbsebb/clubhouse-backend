package fr.hoenheimsports.service.booking.mapper;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.dto.booking.AddressDTO;
import fr.hoenheimsports.repository.booking.entity.booking.AddressEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    AddressEntity toAddressEntity(Address address);
    Address toAddress(AddressEntity addressEntity);

    AddressDTO toAddressDTO(Address address);

}
