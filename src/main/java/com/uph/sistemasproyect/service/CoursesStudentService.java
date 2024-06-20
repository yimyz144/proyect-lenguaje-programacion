package com.uph.sistemasproyect.service;

import com.uph.sistemasproyect.entity.CoursesStudent;
import com.uph.sistemasproyect.repository.CourseRepository;
import com.uph.sistemasproyect.repository.CoursesStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesStudentService {
    @Autowired
    private CoursesStudentRepository repository;

    public void save(CoursesStudent coursesStudent){
       repository.save(coursesStudent);
    }

    public List<CoursesStudent>getList(int id){
        return repository.findById(id);
    }

    public CoursesStudent existsCourse(String username,int idCourse){
        return repository.existsByUsernameAndIdCourse(username,idCourse);
    }

    public Optional<CoursesStudent>getCourseStudent(String username){
        return repository.findByUsername(username);
    }

    public List<CoursesStudent> getCoursesStudent(String username){
        return repository.getCoursesStudent(username);
    }
}
