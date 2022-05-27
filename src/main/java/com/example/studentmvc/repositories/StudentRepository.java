package com.example.studentmvc.repositories;

import com.example.studentmvc.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String name);
    Page<Student> findByNameContains(String keyword, Pageable pageable);
 }
