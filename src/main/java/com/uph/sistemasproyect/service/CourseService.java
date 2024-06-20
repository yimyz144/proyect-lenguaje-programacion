package com.uph.sistemasproyect.service;

import com.uph.sistemasproyect.entity.Course;
import com.uph.sistemasproyect.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public void save(Course course){
        courseRepository.save(course);
    }

    public Optional<Course> getById(int id){
        return courseRepository.findById(id);
    }

    public boolean existById(int id){
        return courseRepository.existsById(id);
    }

    public List<Course> getListAll(){
        return courseRepository.findAll();
    }

    public void delete(int id){
        courseRepository.deleteById(id);
    }
}
