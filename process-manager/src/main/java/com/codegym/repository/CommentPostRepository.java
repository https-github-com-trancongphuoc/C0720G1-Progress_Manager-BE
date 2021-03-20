package com.codegym.repository;

import com.codegym.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface CommentPostRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT * FROM `comment` where `comment`.`id` = ?1", nativeQuery = true)
    Comment findByIdComment(Integer id);

    @Query(value = "SELECT comment.id, comment.content, comment.title, comment.time_comment, comment.account_id, comment.delete_flag, comment.reply_comment_id,comment.status, comment.topic_process_id FROM `comment` " +
            "join `topic_process` on `comment`.`topic_process_id` = `topic_process`.id " +
            "join `info_topic_register` on `topic_process`.`info_topic_register` = `info_topic_register`.id " +
            "where `topic_process`.`info_topic_register` = ?1 and `comment`.`delete_flag` = false and `comment`.`status` = false " +
            "order by `comment`.`time_comment` DESC ", nativeQuery = true)
    Page<Comment> findAllByComment(Integer id, Pageable pageable);

    @Query(value = "SELECT * FROM comment " +
            "where reply_comment_id = ?1 and delete_flag = false " +
            "order by time_comment DESC", nativeQuery = true)
    Page<Comment> findAllByCommentReply(Integer idReply, Pageable pageable);

    @Modifying
    @Query(value = "INSERT INTO `comment`(`content`,`time_comment` ,`account_id`, `status`, `delete_flag` ,`reply_comment_id`) " +
            "value (?1, ?2, ?3, ?4, ?5, ?6) ", nativeQuery = true)
    void createReplyComment(String content, String dateComment, Integer accountId, Boolean status, Boolean deleteFlag, Integer replyCommentId);

    @Modifying
    @Query(value = "INSERT INTO `comment`(`content`,`time_comment` ,`account_id`, `status`, `delete_flag` , `title` ,`topic_process_id`) " +
            "value (?1, ?2, ?3, ?4, ?5, ?6, ?7)", nativeQuery = true)
    void createCommentPost(String content, String dateComment, Integer accountId, Boolean status, Boolean deleteFlag, String title, Integer topicProcessId);

    @Modifying
    @Query(value = "UPDATE `comment` SET `content` = ?1 WHERE `id` = ?2 and account_id = ?3", nativeQuery = true)
    void updateComment(String content, Integer id, Integer accountId);

    @Modifying
    @Query(value = "update `comment` set delete_flag = ?1 where id = ?2 and account_id = ?3", nativeQuery = true)
    void deleteComment(Boolean delete, Integer id, Integer accountId);

    @Modifying
    @Query(value = "update `comment` set delete_flag = ?1 where id = ?2", nativeQuery = true)
    void deleteComment(Boolean delete, Integer id);
}
