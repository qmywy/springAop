package com.fly.view;

import com.fly.bean.Page;
import com.fly.service.StuBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stu")
public class UserController {

    @Autowired
    private StuBus stuBus;

    @RequestMapping(value = "/{page}/{count}",method = RequestMethod.GET)
    public Page selectStudent(@PathVariable("count") Integer count, @PathVariable("page") Integer page){
        return stuBus.selectStudent(page,count);
    }

}
