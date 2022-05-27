package com.example.studentmvc.service;

import com.example.studentmvc.entities.Student;
import com.example.studentmvc.enums.Gender;
import com.example.studentmvc.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@AllArgsConstructor
public class SchoolServiceImpl implements ISchoolService {
    private StudentRepository studentRepository;

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student saveStudent(String name, String lastName, Gender gender) {
        Student student = new Student();
        student.setName(name);
        student.setLastName(lastName);
        student.setDateOfBirth(new Date());
        student.setEmail( name.toLowerCase().replaceAll("\\s+","") + lastName.toLowerCase().replaceAll("\\s+","") + "@gmail.com");
        student.setGender(gender);
        student.setEnRegle(Math.random()<0.5?true:false);
        studentRepository.save(student);
        return student;
    }

    @Override
    public Student findStudentByName(String name) {
        return studentRepository.findByName(name);
    }
}
