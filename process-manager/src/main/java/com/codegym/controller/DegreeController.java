package com.codegym.controller;


import com.codegym.entity.Degree;
import com.codegym.service.DegreeService;
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
public class DegreeController {
    @Autowired
    DegreeService degreeService;


    @RequestMapping(value = "/get-all-degree", method = RequestMethod.GET)
    public ResponseEntity<List<Degree>> getAllDegree(){
        List<Degree> listDegree = degreeService.getAllDegree();
        if (listDegree.isEmpty()){
            return new ResponseEntity<List<Degree>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Degree>>(listDegree, HttpStatus.OK);
    }
}
