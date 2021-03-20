package com.codegym.service.impl;

import com.codegym.entity.Faculty;
import com.codegym.repository.FacultyRepository;
import com.codegym.service.FacultyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    @Autowired
    FacultyRepository facultyRepository;


    /**
     * TinVT
     * Find All Faculty
     */
    @Override
    public List<Faculty> getAllFaculty() {
        return facultyRepository.findAll();
    }
}
