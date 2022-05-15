package com.demo.controller;

import com.demo.entity.TestHoliday;
import com.demo.service.TestHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private TestHolidayService testHolidayService;

    @RequestMapping("a")
    public String a(){
        return "hello,a";
    }
    @RequestMapping("insert")
    public String insert(){
        TestHoliday testHoliday=new TestHoliday();
        testHoliday.setId(UUID.randomUUID().toString());
        testHoliday.setDays(10);
        testHoliday.setRemark("备注asdlfj");
        testHolidayService.insert(testHoliday);
        return "ok";
    }

}
