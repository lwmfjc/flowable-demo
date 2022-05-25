package com.demo.service.impl;

import com.demo.dao.DangerReportMapper;
import com.demo.entity.DangerReport;
import com.demo.service.DangerReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DangerReportServiceImpl implements DangerReportService {

    @Autowired
    private DangerReportMapper dangerReportMapper;

    /**
     * 保存单据
     * @param dangerReport
     * @return
     */
    @Override
    public int save(DangerReport dangerReport) {
        int num = dangerReportMapper.insert(dangerReport);
        return num;
    }

    /**
     * 送审
     * @param id
     */
    @Override
    public void send(String id) {
        //dangerReportMapper
    }
}
