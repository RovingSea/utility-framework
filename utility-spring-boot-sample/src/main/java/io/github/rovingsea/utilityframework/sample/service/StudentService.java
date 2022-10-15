package io.github.rovingsea.utilityframework.sample.service;


import io.github.rovingsea.utilityframework.sample.entity.Student;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface StudentService {

    Student getStudentById(int id);

    List<Student> getStudentsByAge(int age);

}

