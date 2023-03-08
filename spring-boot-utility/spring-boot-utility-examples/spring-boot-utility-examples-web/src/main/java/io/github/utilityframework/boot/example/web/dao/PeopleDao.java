package io.github.utilityframework.boot.example.web.dao;

import io.github.utilityframework.boot.example.web.entity.People;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface PeopleDao {

    People selectStudentById(int id);

    List<People> selectStudentsByAge(int age);

}

