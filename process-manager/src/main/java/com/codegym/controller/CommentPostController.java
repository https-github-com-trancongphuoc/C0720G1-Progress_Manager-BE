package com.codegym.controller;

import com.codegym.dto.CommentPostDTO;
import com.codegym.entity.Comment;
import com.codegym.service.CommentPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/public/process-post")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CommentPostController {

    @Autowired
    CommentPostService commentPostService;

    /**
     * Trung TQ: Thêm mới comment
     */

    @RequestMapping(value = "/create-comment", method = RequestMethod.POST)
    public ResponseEntity<Void> createCommentPost(@Valid @RequestBody CommentPostDTO commentPostDTO, UriComponentsBuilder ucBuilder) {
        commentPostService.createCommentPost(commentPostDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/findById/{id}").buildAndExpand(commentPostDTO.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Trung TQ: Thêm mới reply
     */

    @RequestMapping(value = "/create-reply", method = RequestMethod.POST)
    public ResponseEntity<Void> createReplyComment(@Valid @RequestBody CommentPostDTO commentPostDTO, UriComponentsBuilder ucBuilder) {
        commentPostService.createReplyComment(commentPostDTO);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/findById/{id}").buildAndExpand(commentPostDTO.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    /**
     * Trung TQ: Cập nhập comment
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Comment> updateComment(@PathVariable("id") Integer id, @RequestBody CommentPostDTO commentPostDTO) {
        Comment currentComment = commentPostService.findByIdComment(id);
        if (currentComment == null) {
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
        commentPostService.updateComment(commentPostDTO);
        return new ResponseEntity<Comment>(currentComment, HttpStatus.OK);
    }

    /**
     * Trung TQ: Danh sách comment
     */
    @RequestMapping(value = "findById/{id}/page-comment", method = RequestMethod.GET)
    public ResponseEntity<Page<Comment>> listPostIdComment(@PathVariable("id") Integer id, @PageableDefault(size = 5) Pageable pageable) {
        Page<Comment> comments = commentPostService.findAllByComment(id, pageable);
        if (comments.isEmpty()) {
            return new ResponseEntity<Page<Comment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Comment>>(comments, HttpStatus.OK);
    }

    @RequestMapping(value = "findById/{id}/comment", method = RequestMethod.GET, params = {"page", "size"})
    public ResponseEntity<Page<Comment>> pageProcessIdComment(@RequestParam("page") int page,
                                                              @RequestParam("size") int size,
                                                              @PathVariable("id") Integer id) {
        Page<Comment> comments = commentPostService.findAllByComment(id, PageRequest.of(page, size));
        if (comments.isEmpty()) {
            return new ResponseEntity<Page<Comment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Comment>>(comments, HttpStatus.OK);
    }

    /**
     * Trung TQ: Danh sách reply
     * */
    @RequestMapping(value = "findById/{id}/page-reply", method = RequestMethod.GET)
    public ResponseEntity<Page<Comment>> listCommentIdReply(@PathVariable("id") Integer id, @PageableDefault(size = 20) Pageable pageable) {
        Page<Comment> comments = commentPostService.findAllByCommentReply(id, pageable);
        if (comments.isEmpty()) {
            return new ResponseEntity<Page<Comment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Comment>>(comments, HttpStatus.OK);
    }

    /**
     * Trung TQ: Danh sách reply - dùng để ẩn hiện
     * */
    @RequestMapping(value = "findById/{id}/reply", method = RequestMethod.GET, params = {"page", "size"})
    public ResponseEntity<Page<Comment>> pageCommentIdReply(@RequestParam("page") int page,
                                                            @RequestParam("size") int size,
                                                            @PathVariable("id") Integer id) {
        Page<Comment> comments = commentPostService.findAllByCommentReply(id, PageRequest.of(page, size));
        if (comments.isEmpty()) {
            return new ResponseEntity<Page<Comment>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Page<Comment>>(comments, HttpStatus.OK);
    }
    /**
     * Trung TQ: Xóa một comment
     * */
    @RequestMapping(value = "/delete/{id}/{accountId}", method = RequestMethod.PATCH)
    public ResponseEntity<Comment> deleteVaccination(@PathVariable("id") Integer id, @PathVariable("accountId") Integer accountId) {
        Comment comment = commentPostService.findByIdComment(id);
        if (comment == null) {
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
        commentPostService.deleteComment(id, accountId);
        return new ResponseEntity<Comment>(HttpStatus.OK);
    }

    /**
     * Trung TQ: Xóa một comment
     * */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.PATCH)
    public ResponseEntity<Comment> deleteVaccination(@PathVariable("id") Integer id) {
        Comment comment = commentPostService.findByIdComment(id);
        if (comment == null) {
            return new ResponseEntity<Comment>(HttpStatus.NOT_FOUND);
        }
        commentPostService.deleteComment(id);
        return new ResponseEntity<Comment>(HttpStatus.OK);
    }
}
