package com.bjpowernode.springboot.service;

import com.bjpowernode.springboot.model.Student;

public interface StudentService {
    /**
     * 根据学生Id查询详情
     * @param id
     * @return
     */
    Student queryStudentById(Integer id);
}
