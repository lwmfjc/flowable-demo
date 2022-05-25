package com.demo.controller;

import com.demo.entity.DangerReport;
import com.demo.service.DangerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("hello")
public class HelloController {
    @Autowired
    private DangerReportService dangerReportService;

    @RequestMapping("a")
    public String a(){
        return "hello,a";
    }
    @RequestMapping("insert")
    public String insert(){
        DangerReport testHoliday=new DangerReport();
        testHoliday.setId(UUID.randomUUID().toString());
        testHoliday.setRemark("备注asdlfj");
        dangerReportService.save(testHoliday);
        return "ok";
    }

}
