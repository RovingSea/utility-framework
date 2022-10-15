package io.github.rovingsea.utilityframework.springboot.constrast.dao;

import io.github.rovingsea.utilityframework.springboot.constrast.entity.dto.Student;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface StudentDao {

    Student selectStudentById(int id);

    List<Student> selectStudentsByAge(int age);

}

