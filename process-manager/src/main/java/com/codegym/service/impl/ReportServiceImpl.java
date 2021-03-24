package com.codegym.service.impl;

import com.codegym.dto.ReportDto;
import com.codegym.entity.Report;
import com.codegym.repository.ReportRepository;
import com.codegym.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;


    @Override
    public void createReport(ReportDto reportDto) {
        reportRepository.createReport(reportDto.getContent(), reportDto.getTitle(), reportDto.getUrl(), reportDto.getTopicProcessId(), LocalDateTime.now().toString());
    }

    @Override
    public Optional<Report> findById(Integer id) {
        return reportRepository.findById(id);
    }

    @Override
    public List<Report> getListUrl(Integer id) {
        return reportRepository.getList(id);
    }

}
