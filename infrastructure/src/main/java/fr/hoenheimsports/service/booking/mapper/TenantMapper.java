package fr.hoenheimsports.service.booking.mapper;

import fr.hoenheimsports.bookdomain.model.Address;
import fr.hoenheimsports.bookdomain.model.Tenant;
import fr.hoenheimsports.dto.booking.HallUserDTO;
import fr.hoenheimsports.repository.booking.entity.booking.TenantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TenantMapper {
    TenantEntity toTenantEntity(Tenant tenant);


     Tenant toTenant(TenantEntity tenantEntity);

    @Mapping(source = "address", target = "addressDTO")
    HallUserDTO toTenantDTO(Tenant tenant);

}
