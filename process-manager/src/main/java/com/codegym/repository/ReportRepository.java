package com.codegym.repository;

import com.codegym.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReportRepository extends JpaRepository<Report, Integer> {

//    /** LuyenNT
//     */
//    @Query(value= "select * from report where report.id = ?",nativeQuery= true)
//    Report getById(Integer id);

}
