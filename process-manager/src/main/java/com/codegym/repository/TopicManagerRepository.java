package com.codegym.repository;

import com.codegym.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface TopicManagerRepository extends JpaRepository<Topic, Integer> {
    @Query(value = "SELECT * FROM process_manager.topic ", nativeQuery = true)
    Page<Topic> findAllByTopic(Pageable pageable);

    @Query(value = "SELECT * FROM process_manager.topic where delete_flag = ?1 and topic.name like %?2%", nativeQuery = true)
    Page<Topic> findAllByTopicFind(Boolean delete , String name, Pageable pageable);

    @Query(value = "SELECT * FROM process_manager.topic where topic.id = ?1", nativeQuery = true)
    Topic findByIdTopic(Integer id);

    @Modifying
    @Query(value = "UPDATE `process_manager`.`topic` SET `delete_flag` = ?1 WHERE (`id` = ?2)", nativeQuery = true)
    void deleteTopic(Boolean deleteFlag, Integer id);

    @Modifying
    @Query(value = "INSERT INTO `process_manager`.`topic_process` (" +
            "`date_end`, `date_start`, `percent_process`, " +
            "`process_number`, `status`, `info_topic_register`) " +
            "VALUES (?1, ?2, ?3, ?4, ?5, ?6) " ,nativeQuery = true)
    void createTopicProcess(String dateEnd, String dateStart, Integer percentProcess,
                            Integer processNumber, Boolean status, Integer infoTopicRegister);

    @Modifying
    @Query(value = "UPDATE `process_manager`.`info_topic_register` SET `status` = ?1,`teacher_id` = ?2  WHERE (`id` = ?3);",nativeQuery = true)
    void statusInfo(Boolean status, Integer teacherId, Integer id);
}

