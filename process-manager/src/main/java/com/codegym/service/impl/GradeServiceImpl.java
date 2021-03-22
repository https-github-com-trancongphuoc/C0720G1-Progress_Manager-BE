package com.codegym.service.impl;

import com.codegym.entity.Grade;
import com.codegym.repository.GradeRepository;
import com.codegym.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeRepository gradeRepository;

    /**
     * TinVT
     * Find All Grade
     */
    @Override
    public List<Grade> getAllGrade() {
        return gradeRepository.findAll();
    }
}
