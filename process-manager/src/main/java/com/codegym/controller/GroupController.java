package com.codegym.controller;


import com.codegym.repository.StudentRepository;
import com.codegym.service.GroupAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/")
public class GroupController {

    @Autowired
    private GroupAccountService groupAccountService;

    @Autowired
    private StudentRepository studentRepository;

    @RequestMapping(value = "add-group/{nameGroup}/{accountId}", method = RequestMethod.POST)
    public ResponseEntity<?> addGroup(@PathVariable("nameGroup") String nameGroup,
                                      @PathVariable("accountId") Integer accountId) {
        this.groupAccountService.saveGroup(nameGroup, accountId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "list-student", method = RequestMethod.GET)
    public ResponseEntity<?> listStudent(@PageableDefault(size = 4) Pageable pageable) {
        return new ResponseEntity<>(this.studentRepository.findAllByGroupAccountIsNull(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "accept-join-group/{groupId}/{accountId}", method = RequestMethod.POST)
    public ResponseEntity<?> acceptJoinGroup(@PathVariable("groupId") Integer groupId,
                                             @PathVariable("accountId") Integer accountId) {
        this.groupAccountService.acceptJoinGroup(groupId, accountId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "search-student/{searchName}", method = RequestMethod.GET)
    public ResponseEntity<?> searchStudent(@PageableDefault(size = 4) Pageable pageable,
                                           @PathVariable("searchName") String searchName) {
        return new ResponseEntity<>(this.studentRepository.searchStudent(searchName, pageable), HttpStatus.OK);

    }

    @RequestMapping(value = "list-group", method = RequestMethod.GET)
    public ResponseEntity<?> listGroup(@PageableDefault(size = 10) Pageable pageable) {
        return new ResponseEntity<>(this.studentRepository.findAll(pageable), HttpStatus.OK);

    }
}
