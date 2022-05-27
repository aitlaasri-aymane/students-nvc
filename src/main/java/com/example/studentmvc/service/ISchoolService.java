package com.example.studentmvc.service;

import com.example.studentmvc.entities.Student;
import com.example.studentmvc.enums.Gender;

public interface ISchoolService {
    Student saveStudent(Student student);
    Student saveStudent(String name, String lastName, Gender gender);
    Student findStudentByName(String name);
}
