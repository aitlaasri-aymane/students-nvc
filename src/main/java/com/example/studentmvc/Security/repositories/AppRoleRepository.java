package com.example.studentmvc.Security.repositories;

import com.example.studentmvc.Security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, String> {
    AppRole findByRole(String role);
}
