package com.codegym.service.impl;

import com.codegym.entity.Degree;
import com.codegym.repository.DegreeRepository;
import com.codegym.service.DegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DegreeServiceImpl implements DegreeService {
    @Autowired
    DegreeRepository degreeRepository;

    @Override
    public List<Degree> getAllDegree() {
        return degreeRepository.findAll();
    }
}
