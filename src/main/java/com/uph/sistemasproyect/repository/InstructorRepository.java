package com.uph.sistemasproyect.repository;


import com.uph.sistemasproyect.entity.Instructor;
import com.uph.sistemasproyect.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
    Optional<Instructor> findByLegalIdentifier(String legalIdentifier);
    boolean existsByLegalIdentifier(String legalIdentifier);
}
