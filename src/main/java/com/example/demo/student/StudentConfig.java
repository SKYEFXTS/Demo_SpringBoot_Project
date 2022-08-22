package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.OCTOBER;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student oshan = new Student(
                    "Oshan",
                    "oshan@gmail.com",
                    LocalDate.of(1999, OCTOBER, 27)
            );

            Student sanjana = new Student(
                    "Sanjna",
                    "sanjana@gmail.com",
                    LocalDate.of(2000, OCTOBER, 27)
            );

            repository.saveAll(
                    List.of(oshan, sanjana)
            );
        };
    }
}
