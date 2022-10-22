package io.github.rovingsea.utilityframework.spring.web.contrast.service.impl;

import io.github.rovingsea.utilityframework.spring.web.contrast.dao.StudentDao;
import io.github.rovingsea.utilityframework.spring.web.contrast.entity.dto.Student;
import io.github.rovingsea.utilityframework.spring.web.contrast.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDao studentDao;

    @Override
    public Student getStudentById(int id) {
        return studentDao.selectStudentById(id);
    }

    @Override
    public List<Student> getStudentsByAge(int age) {
        return studentDao.selectStudentsByAge(age);
    }

}

