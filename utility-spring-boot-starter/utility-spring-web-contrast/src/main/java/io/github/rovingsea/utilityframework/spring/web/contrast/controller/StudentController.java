package io.github.rovingsea.utilityframework.spring.web.contrast.controller;

import io.github.rovingsea.utilityframework.spring.web.contrast.entity.ResponseResult;
import io.github.rovingsea.utilityframework.spring.web.contrast.entity.dto.Student;
import io.github.rovingsea.utilityframework.spring.web.contrast.service.StudentService;
import io.github.rovingsea.utilityframework.spring.web.contrast.validator.StudentValidator;
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
@RequestMapping("/contrast")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @RequestMapping("/queryStudentById/{id}")
    public ResponseResult<Student> queryStudentById(@PathVariable int id) {
        StudentValidator.queryStudentById(id);
        Student student = studentService.getStudentById(id);
        return ResponseResult.success(student);
    }

    @RequestMapping("/queryStudentsByAge/{age}")
    public ResponseResult<List<Student>> queryStudentsByAge(@PathVariable int age) {
        StudentValidator.queryStudentsByAge(age);
        List<Student> students = studentService.getStudentsByAge(age);
        return ResponseResult.success(students);
    }

}

