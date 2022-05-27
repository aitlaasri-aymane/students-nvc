package com.example.studentmvc.web;

import com.example.studentmvc.entities.Student;
import com.example.studentmvc.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {
    private StudentRepository studentRepository;

    @GetMapping("/index")
    public String students(Model model,
                           @RequestParam(name = "page", defaultValue = "1") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword
                           ){
        if (page < 1){
            page = 1;
        } else if (size < 5)
            size = 5;
        Page<Student> studentPage = studentRepository.findByNameContains(keyword, PageRequest.of(page-1,size));
        model.addAttribute("studentList",studentPage.getContent());
        model.addAttribute("keyword",keyword);
        model.addAttribute("size",size);
        model.addAttribute("studentTotalPages",new int[studentPage.getTotalPages()]);
        model.addAttribute("studentsTotalPages",studentPage.getTotalPages());
        model.addAttribute("studentResultsPerPage",studentPage.getSize());
        model.addAttribute("studentCurrentPage",studentPage.getNumber());
        return "students";
    }
    @GetMapping("/user/students")
    @ResponseBody
    List<Student> getAllStudents(){
        return studentRepository.findAll();
    }
    @GetMapping("/")
    String home(){
        return "redirect:/index";
    }

    @RequestMapping(value="/admin/deletestudent", method = RequestMethod.DELETE)
    String deleteStudent(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "keyword") String keyword
    ){
        studentRepository.deleteById(id);
        return "redirect:/index?page="+ page +"&keyword="+ keyword +"&size=" + size;
    }
    @GetMapping("/admin/addstudentform")
    String addStudentForm(Model model){
        model.addAttribute("student", new Student());
        return "addstudentform";
    }
    @PostMapping(path = "/admin/save")
    String saveStudent(
            Model model,
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "keyword", defaultValue = "") String keyword,
            @Valid Student student,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors() && student.getId() == null)
            return "addstudentform";
        else if (bindingResult.hasErrors() && student.getId() != null)
            return "editstudentform";
        studentRepository.save(student);
        System.out.println(student);
        return "redirect:/index?page="+ page +"&keyword="+ keyword +"&size=" + size;
    }
    @GetMapping( "/admin/editstudentform")
    String editStudent(
            @RequestParam(name = "id") Long id,
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") int size,
            @RequestParam(name = "keyword") String keyword,
            Model model
    ){
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null)
            throw new RuntimeException("Student cannot be found!");
        model.addAttribute("student", student);
        model.addAttribute("page", page);
        model.addAttribute("size", size);
        model.addAttribute("keyword", keyword);
        return "editstudentform";
    }
}
