package com.uph.sistemasproyect.repository;

import com.uph.sistemasproyect.entity.Category;
import com.uph.sistemasproyect.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
