package com.demo.service.impl;

import com.demo.dao.TestHolidayMapper;
import com.demo.entity.TestHoliday;
import com.demo.service.TestHolidayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestHolidayServiceImpl implements TestHolidayService {

    @Autowired
    private TestHolidayMapper testHolidayMapper;
    @Override
    public int insert(TestHoliday testHoliday) {
        int num = testHolidayMapper.insert(testHoliday);
        return num;
    }
}
