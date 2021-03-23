package com.codegym.service;

import com.codegym.dto.ReportDto;
import com.codegym.entity.Report;

import java.util.Optional;

public interface ReportService {

//    void createReport(Report report);
    void createReport(ReportDto reportDto);
    Optional<Report> findById(Integer id);
}
