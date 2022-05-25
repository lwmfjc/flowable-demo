package com.demo.service;

import com.demo.entity.DangerReport;

public interface DangerReportService {
    int save(DangerReport dangerReport);
    void send(String id);
}
