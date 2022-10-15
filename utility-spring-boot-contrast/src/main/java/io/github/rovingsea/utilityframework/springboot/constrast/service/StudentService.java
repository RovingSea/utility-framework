package io.github.rovingsea.utilityframework.springboot.constrast.service;

import io.github.rovingsea.utilityframework.springboot.constrast.entity.dto.Student;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface StudentService {

    Student getStudentById(int id);

    List<Student> getStudentsByAge(int age);

}

