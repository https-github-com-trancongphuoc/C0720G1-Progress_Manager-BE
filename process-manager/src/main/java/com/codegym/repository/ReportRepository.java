package com.codegym.repository;

import com.codegym.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ReportRepository extends JpaRepository<Report, Integer> {

//    /** LuyenNT
//     */
//    @Query(value= "select * from report where report.id = ?",nativeQuery= true)
//    Report getById(Integer id);

    @Modifying
    @Query(value = "INSERT INTO `process_manager`.`report` (`content`, `title`, `url`, `topic_process_id`,`date`) VALUES (?1, ?2, ?3, ?4, ?5) " ,nativeQuery= true )
    void createReport(String content,String title,String url,Integer id,String date);

    /** LuyenNT
     */
    @Query(value = "SELECT * FROM report where report.topic_process_id = ? order by report.date desc ",nativeQuery = true)
    List<Report> getList(Integer id);
}
