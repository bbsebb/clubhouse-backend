package fr.hoenheimsports.service.booking.mapper;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Tenant;
import fr.hoenheimsports.dto.booking.HallUserDTO;
import fr.hoenheimsports.repository.booking.entity.booking.AssociationHallUserEntity;
import fr.hoenheimsports.repository.booking.entity.booking.HallUserEntity;
import fr.hoenheimsports.repository.booking.entity.booking.TenantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.SubclassMapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",uses = {TenantMapper.class, AssociationHallUserMapper.class})
public abstract class HallUserMapper {


    public HallUserEntity toHallUserEntity(HallUser hallUser) {
        if (hallUser instanceof Tenant) {
            return Mappers.getMapper(TenantMapper.class).toTenantEntity((Tenant) hallUser);
        } else if (hallUser instanceof AssociationHallUser) {
            return Mappers.getMapper(AssociationHallUserMapper.class).toAssociationHallUserEntity((AssociationHallUser) hallUser);
        }
        throw new IllegalArgumentException("Unknown HallUser subtype");
    }

    public HallUser toHallUser(HallUserEntity hallUserEntity) {
        if (hallUserEntity instanceof TenantEntity) {
            return Mappers.getMapper(TenantMapper.class).toTenant((TenantEntity) hallUserEntity);
        } else if (hallUserEntity instanceof AssociationHallUserEntity) {
            return Mappers.getMapper(AssociationHallUserMapper.class).toAssociationHallUser((AssociationHallUserEntity) hallUserEntity);
        }
        throw new IllegalArgumentException("Unknown HallUserEntity subtype");
    }

    public HallUserDTO toHallUserDTO(HallUser hallUser) {
        if (hallUser instanceof Tenant) {
            return Mappers.getMapper(TenantMapper.class).toTenantDTO((Tenant) hallUser);
        } else if (hallUser instanceof AssociationHallUser) {
            return Mappers.getMapper(AssociationHallUserMapper.class).toAssociationHallUserDTO((AssociationHallUser) hallUser);
        }
        throw new IllegalArgumentException("Unknown HallUser subtype");
    }




}
