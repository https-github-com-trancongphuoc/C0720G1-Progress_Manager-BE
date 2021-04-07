package com.codegym.controller;
import com.codegym.dto.AccountRoleDTO;
import com.codegym.dto.CreateUpdateTeacherDTO;
import com.codegym.dto.ITeacherEditDTO;
import com.codegym.entity.Account;
import com.codegym.entity.Teacher;
import com.codegym.service.AccountRoleService;
import com.codegym.service.AccountService;
import com.codegym.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class TeacherController {
    @Autowired
    TeacherService teacherService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRoleService accountRoleService;

    @RequestMapping(value = "/teacher-list", method = RequestMethod.GET)
    public ResponseEntity<Page<Teacher>> getAllTeacher(@RequestParam(defaultValue = "") String find,
                                                       @RequestParam(value = "page") Integer page){
        Page<Teacher> listTeacher = teacherService.getAllTeacher(find, PageRequest.of(page,4));
        if (listTeacher.isEmpty()){
            return new ResponseEntity<Page<Teacher>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Page<Teacher>>(listTeacher, HttpStatus.OK);
    }


    @RequestMapping(value = "/create-teacher", method = RequestMethod.POST)
    public ResponseEntity<CreateUpdateTeacherDTO> createTeacher(@RequestBody CreateUpdateTeacherDTO teacherDTO){
        if (teacherDTO == null){
            return new ResponseEntity<CreateUpdateTeacherDTO>(HttpStatus.BAD_REQUEST);
        }else {
            Account account = new Account();
            account.setUsername(teacherDTO.getEmail());
            account.setPassword("123");
            account = accountService.registerAccount(account);
            teacherDTO.setAccountId(account.getId());

            AccountRoleDTO accountRoleDTO = new AccountRoleDTO();
            accountRoleDTO.setAccountId(account.getId());
            accountRoleDTO.setRoleId(2);

            teacherService.createTeacher(teacherDTO);
            return new ResponseEntity<CreateUpdateTeacherDTO>(teacherDTO, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/delete-teacher/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/get-teacher-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<ITeacherEditDTO> findStudentById(@PathVariable Integer id){
        ITeacherEditDTO teacher = teacherService.findTeacherById(id);
        if (teacher == null){
            return new ResponseEntity<ITeacherEditDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<ITeacherEditDTO>(teacher, HttpStatus.OK);
    }

    @RequestMapping(value = "/update-teacher", method = RequestMethod.PUT)
    public ResponseEntity<?> editStudent(@RequestBody CreateUpdateTeacherDTO teacherDTO){
        teacherService.editTeacher(teacherDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
