package com.codegym.controller;

import com.codegym.dto.ReportDto;
import com.codegym.entity.Report;
import com.codegym.entity.TopicProcess;
import com.codegym.service.ReportService;
import com.codegym.service.TopicProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/public/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private TopicProcessService topicProcessService;

    /**
     * LuyenNT
     * @return
     */
    @RequestMapping(value = "CheckCreateReport/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> checkCreateReport(@PathVariable Integer id) {
        TopicProcess topicProcess = topicProcessService.findById(id);
        List<Report> reportList = reportService.getListUrl(id);
        List list = new ArrayList();
        list.add(topicProcess);
        list.add(reportList);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * LuyenNT
     * //
     */
    @RequestMapping(value = "CreateReport", method = RequestMethod.POST)
    public ResponseEntity<Void> createCommentPost(@Valid @RequestBody ReportDto reportDto, UriComponentsBuilder ucBuilder) {
        reportService.createReport(reportDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/findById/{id}").buildAndExpand(reportDto.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }



    /**
     * LuyenNT
     */
    @RequestMapping(value = "CheckEditFile", method = RequestMethod.GET)
    public ResponseEntity<?> checkEditFileTopic(@RequestParam Integer id) {

        return new ResponseEntity(HttpStatus.OK);
    }
}
