package io.github.rovingsea.utilityframework.spring.web.sample.dao;

import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface PeopleDao {

    People selectStudentById(int id);

    List<People> selectStudentsByAge(int age);

}

