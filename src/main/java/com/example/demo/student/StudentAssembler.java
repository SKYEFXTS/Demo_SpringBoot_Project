package com.example.demo.student;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class StudentAssembler implements SimpleRepresentationModelAssembler<Student> {

    @Override
    public void addLinks(EntityModel<Student> resource) {
        Long studentId = resource.getContent().getId();
        resource.add(linkTo(methodOn(StudentController.class).getStudentById(studentId)).withSelfRel());
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Student>> resources) {
        resources.add(linkTo(methodOn(StudentController.class).getStudents()).withSelfRel());

    }
}
