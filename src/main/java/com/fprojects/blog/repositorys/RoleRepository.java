package com.fprojects.blog.repositorys;

import com.fprojects.blog.entitys.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}
