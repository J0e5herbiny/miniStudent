package com.joe.miniApp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired // inject the dependency
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudent(){
        return studentService.getStudent();
    }

    @PostMapping
    public void postStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudentWithId(id);
    }

    @PutMapping( path = "{studentID}")
    public void updateStudent(@PathVariable("studentID") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email){
        studentService.putStudent(id, name, email);
    }

}
