package fr.hoenheimsports.repository.booking;

import fr.hoenheimsports.bookdomain.model.AssociationHallUser;
import fr.hoenheimsports.bookdomain.model.HallUser;
import fr.hoenheimsports.bookdomain.model.Tenant;
import fr.hoenheimsports.bookdomain.spi.HallUserRepository;
import fr.hoenheimsports.repository.booking.entity.AssociationHallUserEntityRepository;
import fr.hoenheimsports.repository.booking.entity.HallUserEntityRepository;
import fr.hoenheimsports.repository.booking.entity.TenantEntityRepository;
import fr.hoenheimsports.service.booking.mapper.AssociationHallUserMapper;
import fr.hoenheimsports.service.booking.mapper.HallUserMapper;
import fr.hoenheimsports.service.booking.mapper.TenantMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository("bookingHallRepositoryImpl")
public class HallUserRepositoryImpl implements HallUserRepository {
    private final HallUserEntityRepository hallUserEntityRepository;
    private final AssociationHallUserEntityRepository associationHallUserEntityRepository;
    private final TenantEntityRepository tenantEntityRepository;
    private final TenantMapper tenantMapper;
    private final AssociationHallUserMapper associationHallUserMapper;
    private final HallUserMapper hallUserMapper;


    public HallUserRepositoryImpl(HallUserEntityRepository userEntityRepository, AssociationHallUserEntityRepository associationHallUserEntityRepository, TenantEntityRepository tenantEntityRepository, TenantMapper tenantMapper, AssociationHallUserMapper associationHallUserMapper, HallUserMapper hallUserMapper) {
        this.hallUserEntityRepository = userEntityRepository;
        this.associationHallUserEntityRepository = associationHallUserEntityRepository;
        this.tenantEntityRepository = tenantEntityRepository;
        this.tenantMapper = tenantMapper;
        this.associationHallUserMapper = associationHallUserMapper;
        this.hallUserMapper = hallUserMapper;
    }

    @Override
    public Optional<Tenant> findRegisteredUserById(UUID id) {
        return this.tenantEntityRepository.findById(id).map(this.tenantMapper::toTenant);
    }

    @Override
    public Optional<AssociationHallUser> findUnregisteredUserById(UUID id) {
        return this.associationHallUserEntityRepository.findById(id).map(this.associationHallUserMapper::toAssociationHallUser);
    }

    @Override
    public Optional<HallUser> findById(UUID id) {
        return this.hallUserEntityRepository.findById(id).map(this.hallUserMapper::toHallUser);
    }

    @Override
    public HallUser save(Tenant tenant) {
        return this.tenantMapper.toTenant(this.hallUserEntityRepository.save( this.tenantMapper.toTenantEntity(tenant)));
    }

    @Override
    public HallUser save(AssociationHallUser associationHallUser) {
        return this.associationHallUserMapper.toAssociationHallUser(this.hallUserEntityRepository.save(this.associationHallUserMapper.toAssociationHallUserEntity(associationHallUser)));
    }
}
