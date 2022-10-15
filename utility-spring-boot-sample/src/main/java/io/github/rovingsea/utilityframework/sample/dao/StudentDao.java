package io.github.rovingsea.utilityframework.sample.dao;

import io.github.rovingsea.utilityframework.sample.entity.Student;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface StudentDao {

    Student selectStudentById(int id);

    List<Student> selectStudentsByAge(int age);

}

