package com.Techsphere.TechsphereV2Backend.Repository;


import com.Techsphere.TechsphereV2Backend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}