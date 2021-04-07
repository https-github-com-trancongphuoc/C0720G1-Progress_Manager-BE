package com.codegym.controller;
import com.codegym.dto.AccountRoleDTO;
import com.codegym.dto.CreateUpdateStudentDTO;
import com.codegym.dto.IStudentEditDTO;
import com.codegym.entity.Account;
import com.codegym.entity.Student;
import com.codegym.service.AccountRoleService;
import com.codegym.service.AccountService;
import com.codegym.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRoleService accountRoleService;

    /**
     * TinVT
     * Find all student
     */
    @RequestMapping(value = "/student-list",method = RequestMethod.GET)
    public ResponseEntity<Page<Student>> getAllStudent(@RequestParam(defaultValue = "") String find,
                                                       @RequestParam(value = "page") Integer page){
        Page<Student> listStudent = studentService.findAllStudent(find,PageRequest.of(page,8));
        if (listStudent.isEmpty()){
            return new ResponseEntity<Page<Student>>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<Page<Student>>(listStudent, HttpStatus.OK);
    }

    /**
     * TinVT
     * Delete Student By id
     */
    @RequestMapping(value = "/delete-student/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
        studentService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TinVT
     * Edit Student
     */
    @RequestMapping(value = "/edit-student", method = RequestMethod.PUT)
    public ResponseEntity<?> editStudent(@RequestBody CreateUpdateStudentDTO studentDTO){
        studentService.editStudent(studentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TinVT
     * create Student
     */
    @RequestMapping(value = "/create-student")
    public ResponseEntity<CreateUpdateStudentDTO> createStudent(@RequestBody CreateUpdateStudentDTO studentDTO){


        if(studentDTO == null){
            return new ResponseEntity<CreateUpdateStudentDTO>(HttpStatus.BAD_REQUEST);
        }
        Account account = new Account();
        account.setUsername(studentDTO.getEmail());
        account.setPassword("123");
        account = accountService.registerAccount(account);

        AccountRoleDTO accountRoleDTO = new AccountRoleDTO();
        accountRoleDTO.setAccountId(account.getId());
        accountRoleDTO.setRoleId(3);
        accountRoleService.registerAccountRole(accountRoleDTO);

        studentDTO.setAccountId(account.getId());
        studentService.createNewStudent(studentDTO);

        return new ResponseEntity<CreateUpdateStudentDTO>(studentDTO, HttpStatus.OK);
    }

    /**
     * TinVT
     * Find Student By Id
     */
    @RequestMapping(value = "/get-student-by-id/{id}", method = RequestMethod.GET)
    public ResponseEntity<IStudentEditDTO> findStudentById(@PathVariable Integer id){
        IStudentEditDTO student = studentService.findStudentById(id);
        if (student == null){
            return new ResponseEntity<IStudentEditDTO>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<IStudentEditDTO>(student, HttpStatus.OK);
    }
}
