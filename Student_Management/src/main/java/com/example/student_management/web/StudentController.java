package com.example.student_management.web;

import com.example.student_management.entities.Student;
import com.example.student_management.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@Controller
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {

    private StudentRepository studentRepository;
    @GetMapping
    String studentView(Model model,
                       @RequestParam(name="page", defaultValue = "0") int page,
                       @RequestParam(name="size",defaultValue = "5") int size,
                       @RequestParam(name="keyword",defaultValue = "")String keyword){
        Page<Student> pageStudent = studentRepository.findByFirstNameStartingWith(keyword, PageRequest.of(page,size));
        model.addAttribute("pages",new int[pageStudent.getTotalPages()]);
        model.addAttribute("curentPage",page);
        model.addAttribute("studentList",pageStudent.getContent());
        model.addAttribute("keyword",keyword);
        return "students";
    }

    @GetMapping ("/add")
    String addStudentView(Model model,String keyword,String page){
        model.addAttribute("student",new Student());
        model.addAttribute("edit",false);
        model.addAttribute("keyword",keyword);
        model.addAttribute("curentPage",page);
        return "studentForm";
    }
    @PostMapping("/save")
    String addStudent(@Valid Student student, Errors errors,String keyword,String page){
        if(errors.hasErrors()){
            return "studentForm";
        };
        studentRepository.save(student);
        return "redirect:/students?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/delete")
    String deleteStudent(Long id,String keyword,String page){
        studentRepository.deleteById(id);
        return "redirect:/students?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/edit")
    String deleteStudent(Model model,Long id,String keyword,String page){
        Student student = studentRepository.getById(id);
        model.addAttribute("student",student);
        model.addAttribute("edit",true);
        model.addAttribute("keyword",keyword);
        model.addAttribute("curentPage",page);
        return "studentForm";
    }
}
