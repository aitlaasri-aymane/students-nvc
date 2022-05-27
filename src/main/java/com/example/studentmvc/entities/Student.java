package com.example.studentmvc.entities;

import com.example.studentmvc.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString // Data is for getters and setters
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty @Size(min = 4, max = 40) //Spring Validation for forms
    private String name;
    @NotEmpty @Size(min = 4, max = 40) //Spring Validation for forms
    private String lastName;
    @NotEmpty
    @Email //Spring email Validation for forms
    private String email;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull //Spring Validation for forms
    private Date dateOfBirth;
    private boolean enRegle;
    @Enumerated(EnumType.STRING)
    @NotNull
    private Gender gender;
}
