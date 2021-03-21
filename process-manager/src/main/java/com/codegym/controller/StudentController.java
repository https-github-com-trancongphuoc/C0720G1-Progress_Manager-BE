package com.codegym.controller;
import com.codegym.dto.IStudentDTO;
import com.codegym.dto.StudentDTO;
import com.codegym.entity.Student;
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

    /**
     * TinVT
     * Find all student
     */
//    @RequestMapping(value = "/list-student",method = RequestMethod.GET)
//    public ResponseEntity<Page<Student>> getAllStudent(@RequestParam(defaultValue = "") String find,
//                                                        @RequestParam(value = "page") Integer page){
//        Page<Student> listStudent = studentService.findAllStudent(find,PageRequest.of(page,8));
//        if (listStudent.isEmpty()){
//            return new ResponseEntity<Page<Student>>(HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<Page<Student>>(listStudent, HttpStatus.OK);
//    }

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
    public ResponseEntity<?> editStudent(@RequestBody StudentDTO studentDTO){
        studentService.editStudent(studentDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * TinVT
     * Edit Student
     */
    @RequestMapping(value = "/create-student")
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO){
        if(studentDTO == null){
            return new ResponseEntity<StudentDTO>(HttpStatus.BAD_REQUEST);
        }
        studentService.createNewStudent(studentDTO);
        return new ResponseEntity<StudentDTO>(studentDTO, HttpStatus.OK);
    }
}
