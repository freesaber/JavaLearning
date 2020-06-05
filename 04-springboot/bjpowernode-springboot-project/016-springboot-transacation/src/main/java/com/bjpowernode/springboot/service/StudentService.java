package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.Student;

public interface StudentService {
    /**
     * 根据学生Id修改学生信息
     */
    int updateStudentById(Student student);
}
