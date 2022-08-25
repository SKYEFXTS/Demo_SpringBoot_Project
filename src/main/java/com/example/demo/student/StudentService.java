package com.example.demo.student;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentAssembler studentAssembler;

    public StudentService(StudentRepository studentRepository, StudentAssembler studentAssembler) {
        this.studentRepository = studentRepository;
        this.studentAssembler = studentAssembler;
    }

    public ResponseEntity<CollectionModel<EntityModel<Student>>> getStudents () {
        return ResponseEntity.ok(studentAssembler.toCollectionModel(studentRepository.findAll()));
        //return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail((student.getEmail()));
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId){
        boolean exists = studentRepository.existsById(studentId);
        if (!exists){
            throw new IllegalStateException("Student with id " + studentId + " does not exists ");
        }
        studentRepository.deleteById(studentId);

    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exists "));

        if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)){
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("Email taken");
            }
            student.setEmail(email);
        }
    }

    public ResponseEntity<Student> getStudentById(Long studentId) {

        return studentRepository.findById(studentId)
                .map(student -> {
                    student.add(linkTo(methodOn(StudentController.class).getStudentById(studentId)).withRel("Get by ID "));
                    student.add(linkTo(methodOn(StudentController.class).getStudents()).withRel("All students"));
                    return ResponseEntity.ok(student);
                })
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exists "));}
}
