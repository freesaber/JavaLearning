package com.bjpowernode.springboot.service.impl;

import com.bjpowernode.springboot.mapper.StudentMapper;
import com.bjpowernode.springboot.model.Student;
import com.bjpowernode.springboot.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    @Override
    public int updateStudentById(Student student) {
        // 修改成功
        int i = studentMapper.updateByPrimaryKeySelective(student);

        // 失败
        int a = 10/0;

        return 0;
    }
}
