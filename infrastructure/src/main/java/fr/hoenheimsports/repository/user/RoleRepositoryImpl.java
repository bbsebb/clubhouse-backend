package fr.hoenheimsports.repository.user;

import fr.hoenheimsports.repository.user.entity.RoleEntityRepository;
import fr.hoenheimsports.service.mapper.RoleMapper;
import fr.hoenheimsports.userdomain.model.Role;
import fr.hoenheimsports.userdomain.spi.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    private final RoleEntityRepository roleEntityRepository;
    private final RoleMapper  roleMapper;

    public RoleRepositoryImpl(RoleEntityRepository roleEntityRepository, RoleMapper roleMapper) {
        this.roleEntityRepository = roleEntityRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public Optional<Role> findByRoleName(String roleName) {
        return this.roleEntityRepository.findByRoleName(roleName).map(this.roleMapper::roleEntityToRole);
    }

    @Override
    public Optional<Role> findById(UUID id) {
        return this.roleEntityRepository.findById(id).map(this.roleMapper::roleEntityToRole);
    }

    @Override
    public Role save(Role role) {
        return this.roleMapper.roleEntityToRole(this.roleEntityRepository.save(this.roleMapper.roleToRoleEntity(role)));
    }

    @Override
    public Set<Role> findAll() {
        return this.roleEntityRepository.findAll().stream().map(roleMapper::roleEntityToRole).collect(Collectors.toSet());
    }
}
