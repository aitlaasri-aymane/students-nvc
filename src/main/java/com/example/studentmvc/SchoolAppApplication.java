package com.example.studentmvc;

import com.example.studentmvc.Security.service.SecurityService;
import com.example.studentmvc.enums.Gender;
import com.example.studentmvc.service.ISchoolService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SchoolAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchoolAppApplication.class, args);
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //@Bean //Comment this so it doesnt execute
    CommandLineRunner start(ISchoolService schoolService){
        return args -> {
            //Add students
            schoolService.saveStudent("Aymane","AIT LAASRI", Gender.MALE);
            schoolService.saveStudent("Omar","Lmahzoz", Gender.MALE);
            schoolService.saveStudent("Hiba","Afifi", Gender.FEMALE);
            schoolService.saveStudent("Chaima","Aderdour", Gender.FEMALE);
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUser("user1","1234","1234");
            securityService.saveNewUser("admin","1234","1234");

            securityService.saveNewRole("USER");
            securityService.saveNewRole("ADMIN");

            securityService.addRoleTouser("user1","USER");
            securityService.addRoleTouser("admin","USER");
            securityService.addRoleTouser("admin","ADMIN");
        };
    }
}
