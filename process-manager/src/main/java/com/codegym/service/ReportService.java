package com.codegym.service;

import com.codegym.entity.Report;

import java.util.Optional;

public interface ReportService {

    void createReport(Report report);

    Optional<Report> findById(Integer id);
}
