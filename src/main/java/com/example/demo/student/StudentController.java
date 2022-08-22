package com.example.demo.student;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path= "api/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    @Operation(
            summary = "Displays students in the database",
            description = "Displays all the students in the database as a json list")
    public List<Student> getStudents () {
        return studentService.getStudents();
        //Link link = new Link("http://localhost:8080/api/student");
    }




    @GetMapping(path = "{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable("studentId") Long studentId){
        return studentService.getStudentById(studentId);
    }




    @PostMapping
    @Operation(
            summary = "Register a new student",
            description = "Adds a new student to the database using data in the request body" )
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path="{studentId}")
    @Operation(
            summary = "Deletes an existing student",
            description = "Deletes an existing student from the database when the student ID is provided")
    public Class<?> deleteStudent (
            @Parameter(
                    name = "Student ID",
                    description= "ID value for the student user want to delete",
                    required = true)
            @PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return null;
    }



    @PutMapping(path= "{studentId}")
    @Operation(
            summary = "Edits an existing student",
            description = "Edit the name or the email of an existing student from the database when the student ID and the name or email are provided")
    public void updateStudent(
            @Parameter(
                    name = "Student ID",
                    description = "ID value for the student user want to edit",
                    required = true)
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email){
        studentService.updateStudent(studentId, name, email);
    }
}
