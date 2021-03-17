package com.codegym.service.impl;

import com.codegym.entity.Report;
import com.codegym.repository.ReportRepository;
import com.codegym.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Override
    public void createReport(Report report) {
        reportRepository.save(report);
    }

    @Override
    public Report findById(Integer id) {
        return reportRepository.getById(id);
    }

}
