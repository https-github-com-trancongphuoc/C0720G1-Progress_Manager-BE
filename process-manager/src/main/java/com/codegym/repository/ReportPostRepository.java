package com.codegym.repository;

import com.codegym.entity.Report;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ReportPostRepository extends JpaRepository<Report, Integer> {

    @Query(value = "SELECT * FROM process_manager.report " +
            "join topic_process on report.topic_process_id = topic_process.id " +
            "join info_topic_register on topic_process.info_topic_register = info_topic_register.id " +
            "where topic_process.info_topic_register = ?1 " +
            "order by `date` DESC ", nativeQuery = true)
    Page<Report> findAllByReport(Integer id, Pageable pageable);
}
