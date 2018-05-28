package com.fly.service;

import com.fly.bean.Student;
import com.fly.dao.MyStudentMapper;
import com.fly.bean.Page;
import com.fly.util.LogType;
import com.fly.util.SystemLog;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StuBus {

    @Autowired
    private MyStudentMapper mapper ;

    @SystemLog(type = LogType.STUDENT_QUERY)
    public void testAopAround(){
        System.out.print("hello-world");
    }

    @SystemLog(type = LogType.STUDENT_QUERY)
    public Page<Student> selectStudent(Integer page, Integer size){

        PageHelper.startPage(page,size);
        PageInfo<Student> pageInfo = new PageInfo<Student>(mapper.selectStudent());
        return new Page<Student>(pageInfo,0);

    }

    @SystemLog(type = LogType.DELETE)
    public boolean deleteStudent(Integer id){
        Integer COUNT = mapper.deleteByPrimaryKey(id);
        if(COUNT>0)
            return true;
        return false;
    }

}
