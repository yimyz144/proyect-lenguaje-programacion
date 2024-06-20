package com.uph.sistemasproyect.service;


import com.uph.sistemasproyect.entity.Student;
import com.uph.sistemasproyect.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public List<Student> list(){
        return studentRepository.findAll();
    }

    public Optional<Student> getOne(int id){
        return studentRepository.findById(id);
    }

    public Optional<Student> getByDni(String dni){
        return studentRepository.findByDni(dni);
    }

    public void  save(Student student){
        studentRepository.save(student);
    }

    public void delete(int id){
        studentRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return studentRepository.existsById(id);
    }

    public boolean existsByDni(String dni) {
        return studentRepository.existsByDni(dni);
    }

}
