package com.emazon.services.user.dao;

import com.emazon.services.user.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsRoleByRolesName(String rolesName);
    Role findRoleCredentialsByRolesName(String rolesName);
}
