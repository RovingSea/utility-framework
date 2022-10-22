package io.github.rovingsea.utilityframework.spring.web.contrast.dao;

import io.github.rovingsea.utilityframework.spring.web.contrast.entity.dto.Student;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface StudentDao {

    Student selectStudentById(int id);

    List<Student> selectStudentsByAge(int age);

}

