package com.codegym.controller;
import com.codegym.dto.CreateUpdateTeacherDTO;
import com.codegym.entity.Teacher;
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
    public ResponseEntity<CreateUpdateTeacherDTO> createTeacher(@RequestBody CreateUpdateTeacherDTO createUpdateTeacherDTO){
        if (createUpdateTeacherDTO == null){
            return new ResponseEntity<CreateUpdateTeacherDTO>(HttpStatus.BAD_REQUEST);
        }else {
            teacherService.createTeacher(createUpdateTeacherDTO);
            return new ResponseEntity<CreateUpdateTeacherDTO>(createUpdateTeacherDTO, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/delete-teacher/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable Integer id){
        teacherService.deleteTeacherById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
