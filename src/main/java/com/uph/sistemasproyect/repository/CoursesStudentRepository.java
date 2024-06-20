package com.uph.sistemasproyect.repository;

import com.uph.sistemasproyect.entity.CoursesStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesStudentRepository extends JpaRepository<CoursesStudent, Integer> {
    List<CoursesStudent>findById(int id);
    Optional<CoursesStudent>findByUsername(String username);
    @Query("from CoursesStudent c where c.username = ?1 and c. idCourse= ?2 ")
    CoursesStudent existsByUsernameAndIdCourse(String username, int idCourse);

    @Query("from CoursesStudent c where c.username=?1")
    List<CoursesStudent>getCoursesStudent(String username);
}
