package com.codegym.controller;


import com.codegym.entity.Grade;
import com.codegym.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class GradeController {
    @Autowired
    GradeService gradeService;


    /**
     * TinVT
     * Find all grade
     */
    @RequestMapping(value = "/get-all-grade", method = RequestMethod.GET)
    public ResponseEntity<List<Grade>> getAllGrade(){
        List<Grade> listGrade = gradeService.getAllGrade();
        if(listGrade.isEmpty()){
            return new ResponseEntity<List<Grade>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Grade>>(listGrade, HttpStatus.OK);
    }
}
