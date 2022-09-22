package com.example.demo.student;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentAssembler implements SimpleRepresentationModelAssembler<Student> {

    @Override
    public void addLinks(EntityModel<Student> resource) {
        Long studentId = resource.getContent().getId();
        if(studentId != null){
            resource.add(linkTo(methodOn(StudentController.class).getStudentById(studentId)).withSelfRel());
        }
    }

    @Override
    public void addLinks(CollectionModel<EntityModel<Student>> resources) {
        resources.add(linkTo(methodOn(StudentController.class).getStudents()).withSelfRel());


    }
}
