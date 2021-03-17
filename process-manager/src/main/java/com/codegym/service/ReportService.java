package com.codegym.service;

import com.codegym.entity.Report;

public interface ReportService {

    void createReport(Report report);

    Report findById(Integer id);
}
