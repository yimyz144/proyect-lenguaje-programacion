package com.uph.sistemasproyect.service;

import com.uph.sistemasproyect.entity.Instructor;
import com.uph.sistemasproyect.repository.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InstructorService {
    @Autowired
    private InstructorRepository instructorRepository;

    public void save(Instructor instructor){
        instructorRepository.save(instructor);
    }

    public List<Instructor>getListAll(){
        return instructorRepository.findAll();
    }

    public boolean existsByDni(String dni) {
        return instructorRepository.existsByLegalIdentifier(dni);
    }

    public Optional<Instructor>getById(int id){
        return instructorRepository.findById(id);
    }

    public boolean existById(int id){
        return instructorRepository.existsById(id);
    }

    public void delete(int id){
        instructorRepository.deleteById(id);
    }
}
