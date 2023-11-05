package com.joe.miniApp.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudent(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional =
                studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()){
            throw new IllegalStateException("Email Taken");
        }
        studentRepository.save(student);
//        System.out.println(student);
    }

    public void deleteStudentWithId(Long id) {
        boolean studentExists = studentRepository.existsById(id);
        if (!studentExists){
            throw new IllegalStateException("This student ID:"+id+" does not exist");
        }
        studentRepository.deleteById(id);
    }

    @Transactional // Makes us DON'T use any queries from our repository
    public void putStudent(Long id,
                           String name,
                           String email) {
        Student student = studentRepository
                .findById(id)
                .orElseThrow(() -> new IllegalStateException("This student with ID:" + id + "doesn't exist"));

        if ( name != null && name.length() > 0 && !Objects.equals(student.getName(), name) ){
            student.setName(name);
        }

        if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email) ){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if (studentByEmail.isPresent()){
                throw new IllegalStateException("Email is taken");
            }
            student.setEmail(email);
        }
    }
}
