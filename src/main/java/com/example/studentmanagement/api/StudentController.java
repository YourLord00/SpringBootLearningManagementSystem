package com.example.studentmanagement.api;

import com.example.studentmanagement.exceptions.InvalidUniversityClassException;
import com.example.studentmanagement.exceptions.StudentEmptyNameException;
import com.example.studentmanagement.model.Student;
import com.example.studentmanagement.service.StudentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {

    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @RequiresPermissions("student:read")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/name")
    public List<Student> getStudents(@RequestParam String name) {
        return studentService.getStudentsByName(name);
    }

    @GetMapping("/contain_name")
    public List<Student> getStudentsContainName(@RequestParam String name) {
        return studentService.getStudentsContiansNme(name);
    }

    @GetMapping("/class")
    public List<Student> getStudentsInClass(@RequestParam int year, @RequestParam int number) {
        return studentService.getStudentsInClass(year, number);
    }

    @RequestMapping("/register")
    @PostMapping
    public ResponseEntity<String> registerStudent(@RequestBody Student student) {
        try {
            Student savedStudent = studentService.addStudent(student);
            return ResponseEntity.ok("Registered student. " + savedStudent.toString());
        } catch(StudentEmptyNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PostMapping(path = "assignclass/{sid}/{cid}")
    public ResponseEntity<String> assignclass(@PathVariable("sid") Long studentId,
                                              @PathVariable("cid") Long classId) {
        try {
            Student assignedClassForStudent = studentService.assignStudentUniversityClass(studentId, classId);
            return ResponseEntity.ok("Registered student. " + assignedClassForStudent.toString());
        } catch(StudentEmptyNameException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch(InvalidUniversityClassException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
