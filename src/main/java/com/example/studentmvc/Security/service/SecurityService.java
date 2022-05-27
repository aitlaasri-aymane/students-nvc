package com.example.studentmvc.Security.service;

import com.example.studentmvc.Security.entities.AppRole;
import com.example.studentmvc.Security.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUser(String username, String password, String verifyPassword);
    AppRole saveNewRole(String role);
    void addRoleTouser(String username, String role);
    void removeRoleFromuser(String username, String role);
    AppUser loadUserByUsername(String username);
}
