package com.uph.sistemasproyect.controller;

import com.uph.sistemasproyect.entity.Course;
import com.uph.sistemasproyect.entity.CoursesStudent;
import com.uph.sistemasproyect.service.CourseService;
import com.uph.sistemasproyect.service.CoursesStudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @Autowired
    private CoursesStudentService coursesStudentService;


    @GetMapping("lista")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/course/lista");
        List<Course> courses = courseService.getListAll();
        mv.addObject("courses", courses);
        return mv;
    }

    @GetMapping("courses-student")
    public ModelAndView listCoursesByStudent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<CoursesStudent>coursesStudents = coursesStudentService.getCoursesStudent(auth.getName());
        List<Course>coursesStudentNew= new ArrayList<>();
        for (CoursesStudent courses: coursesStudents){
         Optional<Course> course = courseService.getById(courses.getIdCourse());
            course.ifPresent(coursesStudentNew::add);
        }
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/course/courses-student");
        mv.addObject("coursesStudentNew", coursesStudentNew);
        return mv;
    }



    @GetMapping("nuevo")
    public String nuevo() {
        return "course/nuevo";
    }

    @PostMapping("/guardar")
    public ModelAndView crear(@RequestParam String description,
                              @RequestParam String name,
                              @RequestParam String startDate,
                              @RequestParam String endDate,
                              @RequestParam String numberHour,
                              @RequestParam String schedule
    ) {
        ModelAndView mv = new ModelAndView();
        Course course = new Course();
        course.setCodeCourses(UUID.randomUUID());
        course.setDescription(description);
        course.setName(name);
        course.setStartDate(LocalDate.parse(startDate));
        course.setEndDate(LocalDate.parse(endDate));
        course.setNumberHour(Integer.parseInt(numberHour));
        course.setSchedule(schedule);
        courseService.save(course);
        mv.setViewName("redirect:/course/lista");
        return mv;
    }

    @GetMapping("/detalle/{id}")
    public ModelAndView detalle(@PathVariable("id") int id) {
        if (!courseService.existById(id))
            return new ModelAndView("redirect:/course/lista");
        Course course = courseService.getById(id).get();
        ModelAndView mv = new ModelAndView("/course/detalle");
        mv.addObject("course", course);
        return mv;
    }

    @GetMapping("/detalle-matricula/{id}")
    public ModelAndView detalleMatricula(@PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CoursesStudent coursesStudent = new CoursesStudent();
        if (!courseService.existById(id))
            return new ModelAndView("redirect:/course/lista");
        Course course = courseService.getById(id).get();
        coursesStudent.setIdCourse(id);
        coursesStudent.setUsername(auth.getName());
        ModelAndView mv = new ModelAndView("/course/detalle-matricula");
        CoursesStudent validatedCourse = null;
        validatedCourse = coursesStudentService.existsCourse(auth.getName(),id);
        if (validatedCourse!= null){
            mv.addObject("error", "Ya estas matriculado en este curso");
            mv.setViewName("course/lista");
            return mv;
        }
        coursesStudentService.save(coursesStudent);
        mv.addObject("course", course);
        return mv;
    }

    @GetMapping("/detalle-matricula-error")
    public String login() {
        return "detalle-matricula-error";
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        if (!courseService.existById(id))
            return new ModelAndView("redirect:/course/lista");
        Course course = courseService.getById(id).get();
        ModelAndView mv = new ModelAndView("/course/editar");
        mv.addObject("course", course);
        return mv;
    }

    @PostMapping("/actualizar")
    public ModelAndView actualizar(
            @RequestParam int id,
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String startDate,
            @RequestParam String endDate,
            @RequestParam String numberHour,
            @RequestParam String schedule) {
        if (!courseService.existById(id))
            return new ModelAndView("redirect:/course/lista");
        ModelAndView mv = new ModelAndView();
        Course course = courseService.getById(id).get();
        if (StringUtils.isBlank(name) || StringUtils.isBlank(description) || StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate)
                || StringUtils.isBlank(numberHour) || StringUtils.isBlank(schedule)) {
            mv.setViewName("course/editar");
            mv.addObject("course", course);
            mv.addObject("error", "Los campos no pueden estar vacios");
            return mv;
        }
        course.setName(name);
        course.setDescription(description);
        course.setStartDate(LocalDate.parse(startDate));
        course.setEndDate(LocalDate.parse(endDate));
        course.setNumberHour(Integer.parseInt(numberHour));
        course.setSchedule(schedule);
        courseService.save(course);
        return new ModelAndView("redirect:/course/lista");
    }

    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id") int id) {
        if (courseService.existById(id)) {
            courseService.delete(id);
            return new ModelAndView("redirect:/course/lista");
        }
        return null;
    }
}
