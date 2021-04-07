package com.codegym.controller;

import com.codegym.dto.AccountDTO;
import com.codegym.dto.AccountRoleDTO;
import com.codegym.dto.StudentCreateDTO;
import com.codegym.dto.TeacherCreateDTO;
import com.codegym.entity.Account;
import com.codegym.entity.AccountRole;
import com.codegym.service.AccountRoleService;
import com.codegym.service.AccountService;
import com.codegym.service.StudentAndTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/public/teacher-student")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StudentAndTeacherController {
    @Autowired
    StudentAndTeacherService service;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRoleService accountRoleService;

    /**
     * TrungTQ: Thêm mới học sinh theo danh sách bằng file excel
     * */
    @RequestMapping(value = "/create-student", method = RequestMethod.POST)
    public ResponseEntity<Void> createStudent(@Valid @RequestBody List<StudentCreateDTO> studentCreateDTOS, UriComponentsBuilder ucBuilder) {

        for (StudentCreateDTO studentCreateDTO : studentCreateDTOS){
            Account account = new Account();
            account.setUsername(studentCreateDTO.getEmail());
            account.setPassword("1234");
            account = accountService.registerAccount(account);

            AccountRoleDTO accountRoleDTO = new AccountRoleDTO();
            accountRoleDTO.setAccountId(account.getId());
            accountRoleDTO.setRoleId(3);
            accountRoleService.registerAccountRole(accountRoleDTO);

            studentCreateDTO.setAccountId(account.getId());
            service.createStudent(studentCreateDTO);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    /**
     * TrungTQ: Thêm mới giảng viên theo danh sách bằng file excel
     * */
    @RequestMapping(value = "/create-teacher", method = RequestMethod.POST)
    public ResponseEntity<Void> createTeacher(@Valid @RequestBody List<TeacherCreateDTO> teacherCreateDTOS, UriComponentsBuilder ucBuilder) {
        for (TeacherCreateDTO teacherCreateDTO : teacherCreateDTOS){
            Account account = new Account();
            account.setUsername(teacherCreateDTO.getEmail());
            account.setPassword("1234");
            account = accountService.registerAccount(account);
            AccountRoleDTO accountRoleDTO = new AccountRoleDTO();
            accountRoleDTO.setAccountId(account.getId());
            accountRoleDTO.setRoleId(2);
            accountRoleService.registerAccountRole(accountRoleDTO);

            teacherCreateDTO.setAccountId(account.getId());
            service.createTeacher(teacherCreateDTO);
        }
        return new ResponseEntity<Void>( HttpStatus.CREATED);
    }
}
