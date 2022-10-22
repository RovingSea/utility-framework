package io.github.rovingsea.utilityframework.spring.web.sample.controller;

import io.github.rovingsea.utilityframework.spring.web.sample.entity.Student;
import io.github.rovingsea.utilityframework.spring.web.sample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/queryStudentById/{id}")
    public Student queryStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @RequestMapping("/queryStudentsByAge/{age}")
    public List<Student> queryStudentsByAge(@PathVariable int age) {
        return studentService.getStudentsByAge(age);
    }

}

