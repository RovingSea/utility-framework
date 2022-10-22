package io.github.rovingsea.utilityframework.spring.web.contrast.service;

import io.github.rovingsea.utilityframework.spring.web.contrast.entity.dto.Student;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface StudentService {

    Student getStudentById(int id);

    List<Student> getStudentsByAge(int age);

}

