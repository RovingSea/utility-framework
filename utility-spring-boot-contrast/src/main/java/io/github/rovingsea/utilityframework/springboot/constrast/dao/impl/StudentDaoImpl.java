package io.github.rovingsea.utilityframework.springboot.constrast.dao.impl;

import io.github.rovingsea.utilityframework.springboot.constrast.dao.StudentDao;
import io.github.rovingsea.utilityframework.springboot.constrast.entity.dto.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Repository
public class StudentDaoImpl implements StudentDao {

    private static List<Student> students;

    static {
        students = new ArrayList<>();
        students.add(new Student(1, "rovingsea", 22));
        students.add(new Student(2, "1024shao", 22));
    }

    @Override
    public Student selectStudentById(int id) {
        return students.stream()
                .filter(s -> s.getId() == id)
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Student> selectStudentsByAge(int age) {
        return students.stream()
                .filter(s -> s.getAge() == age)
                .collect(Collectors.toList());
    }

}

