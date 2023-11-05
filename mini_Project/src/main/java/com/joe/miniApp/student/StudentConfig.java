package com.joe.miniApp.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student ahmed = new Student(
                    "ahmed",
                    "ahmed@email",
                    LocalDate.of(2001, Month.JANUARY, 1)
            );
            Student amr = new Student(
                    "amr",
                    "amr@email",
                    LocalDate.of(2002, Month.FEBRUARY, 2)
            );

            studentRepository.saveAll( List.of(ahmed, amr) );
        };
    }

}
