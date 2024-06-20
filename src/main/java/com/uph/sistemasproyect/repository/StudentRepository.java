package com.uph.sistemasproyect.repository;


import com.uph.sistemasproyect.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByDni(String legalIdentifier);
    boolean existsByDni(String legalIdentifier);
}
