package com.codegym.controller;

import com.codegym.entity.Report;
import com.codegym.entity.TopicProcess;
import com.codegym.service.ReportService;
import com.codegym.service.TopicProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class ReportController {

    @Autowired
    private ReportService reportService;
    @Autowired
    private TopicProcessService topicProcessService;

    /** LuyenNT
     */
    @RequestMapping(value = "CheckCreateReport/{id}",method = RequestMethod.GET)
    public ResponseEntity<TopicProcess> checkCreateReport(@PathVariable Integer id){
        TopicProcess topicProcess;
        topicProcess = topicProcessService.findById(id);
            return new ResponseEntity<TopicProcess>(topicProcess, HttpStatus.OK);
    }

    /** LuyenNT
     */
    @RequestMapping(value = "CreateReport",method = RequestMethod.POST)
    public ResponseEntity<?> CreateReport(@RequestBody Report report ){
        reportService.createReport(report);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /** LuyenNT
     */
    @RequestMapping(value = "CheckEditFile",method = RequestMethod.GET)
    public ResponseEntity<?> checkEditFileTopic(@RequestParam Integer id){

        return new ResponseEntity(HttpStatus.OK);
    }
}