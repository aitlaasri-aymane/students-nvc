package com.example.studentmvc.Security.service;

import com.example.studentmvc.Security.entities.AppRole;
import com.example.studentmvc.Security.entities.AppUser;
import com.example.studentmvc.Security.repositories.AppRoleRepository;
import com.example.studentmvc.Security.repositories.AppUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUser(String username, String password, String verifyPassword) {
        if (!password.equals(verifyPassword))
            throw new RuntimeException("Passwords dont match!");
        String hachedPWD = passwordEncoder.encode(password);
        AppUser appUser = new AppUser();
        appUser.setUsername(username);
        appUser.setUserID(UUID.randomUUID().toString());
        appUser.setPassword(hachedPWD);
        appUser.setActive(true);
        System.out.println(appUser);
        appUserRepository.save(appUser);
        return appUser;
    }

    @Override
    public AppRole saveNewRole(String role) {
        if (appRoleRepository.findByRole(role) != null)
            throw new RuntimeException("Role already exists!");
        AppRole appRole = new AppRole();
        appRole.setRole(role);
        appRoleRepository.save(appRole);
        return appRole;
    }

    @Override
    public void addRoleTouser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRole(role);
        appUser.getUserRoles().add(appRole);
        appUserRepository.save(appUser);
    }

    @Override
    public void removeRoleFromuser(String username, String role) {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null)
            throw new RuntimeException("User doesnt exist!");
        AppRole appRole = appRoleRepository.findByRole(role);
        if (appRole == null)
            throw new RuntimeException("Role doesnt exist!");
        appUser.getUserRoles().remove(appRole);
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }
}
