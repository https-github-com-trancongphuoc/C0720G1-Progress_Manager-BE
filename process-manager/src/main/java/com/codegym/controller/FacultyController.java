package com.codegym.controller;


import com.codegym.entity.Faculty;
import com.codegym.service.FacultyService;
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
public class FacultyController {
    @Autowired
    FacultyService facultyService;

    @RequestMapping(value = "/get-all-faculty", method = RequestMethod.GET)
    public ResponseEntity<List<Faculty>> getAllFaculty(){
        List<Faculty> listFaculty =  facultyService.getAllFaculty();
        if (listFaculty.isEmpty()){
            return new ResponseEntity<List<Faculty>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<List<Faculty>>(listFaculty,HttpStatus.OK);
    }
}
