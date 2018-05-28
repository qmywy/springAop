package com.fly.dao;

import com.fly.bean.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyStudentMapper {

    List<Student> selectStudent();

    int deleteByPrimaryKey(Integer id);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}