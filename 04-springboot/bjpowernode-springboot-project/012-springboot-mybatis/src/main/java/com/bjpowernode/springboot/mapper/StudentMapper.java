package com.bjpowernode.springboot.mapper;

import com.bjpowernode.springboot.model.Student;
import org.apache.ibatis.annotations.Mapper;

@Mapper // 扫描dao接口到spring容器
public interface StudentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}