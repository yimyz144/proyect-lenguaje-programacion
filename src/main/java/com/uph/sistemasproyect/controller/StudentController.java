package com.uph.sistemasproyect.controller;

import com.uph.sistemasproyect.entity.Address;
import com.uph.sistemasproyect.entity.Student;
import com.uph.sistemasproyect.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;

    @GetMapping("lista")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/student/lista");
        List<Student> students = studentService.list();
        mv.addObject("students", students);
        return mv;
    }

    @GetMapping("nuevo")
    public String nuevo() {
        return "student/nuevo";
    }

    @PostMapping("/guardar")
    public ModelAndView crear(@RequestParam String dni,
                              @RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String birthDate,
                              @RequestParam String email,
                              @RequestParam String mobile,
                              @RequestParam String street,
                              @RequestParam String numberStreet,
                              @RequestParam String province,
                              @RequestParam String city,
                              @RequestParam String codePostal) {
        ModelAndView mv = new ModelAndView();
        if (StringUtils.isBlank(dni)) {
            mv.setViewName("student/nuevo");
            mv.addObject("error", "El Dni/Nie no puede estar vacío");
            return mv;
        }
        if (studentService.existsByDni(dni)) {
            mv.setViewName("student/nuevo");
            mv.addObject("error", "El Dni/Nie ya existe");
            return mv;
        }
        Address address = new Address();
        address.setStreet(street);
        address.setNumberStreet(numberStreet);
        address.setProvince(province);
        address.setCity(city);
        address.setCodePostal(codePostal);
        Student student = new Student();
        student.setDni(dni);
        student.setSurname(surname);
        student.setName(name);
        student.setBirthDate(LocalDate.parse(birthDate));
        student.setEmail(email);
        student.setMobile(mobile);
        student.setAge(calculateAgeByBirthDate(LocalDate.parse(birthDate)));
        student.setAddress(address);
        studentService.save(student);
        mv.setViewName("redirect:/index");
        return mv;
    }


    private int calculateAgeByBirthDate(LocalDate birthDate) {
        Period period = Period.between(birthDate, LocalDate.now());
        return period.getYears();
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        if (!studentService.existsById(id))
            return new ModelAndView("redirect:/student/lista");
        Student student = studentService.getOne(id).get();
        ModelAndView mv = new ModelAndView("/student/editar");
        mv.addObject("student", student);
        return mv;
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/actualizar")
    public ModelAndView actualizar(
            @RequestParam int id,
            @RequestParam String email,
            @RequestParam String mobile,
            @RequestParam String street,
            @RequestParam String numberStreet,
            @RequestParam String province,
            @RequestParam String city,
            @RequestParam String codePostal)

    {
        if (!studentService.existsById(id))
            return new ModelAndView("redirect:/student/lista");
        ModelAndView mv = new ModelAndView();
        Student student = studentService.getOne(id).get();
        if (StringUtils.isBlank(email) || StringUtils.isBlank(mobile) || StringUtils.isBlank(street) || StringUtils.isBlank(numberStreet)
        || StringUtils.isBlank(province) || StringUtils.isBlank(city) || StringUtils.isBlank(codePostal)) {
            mv.setViewName("student/editar");
            mv.addObject("student", student);
            mv.addObject("error", "Los campos no pueden estar vacios");
            return mv;
        }

        Address address = student.getAddress();
        address.setStreet(street);
        address.setNumberStreet(numberStreet);
        address.setProvince(province);
        address.setCity(city);
        address.setCodePostal(codePostal);
        student.setEmail(email);
        student.setMobile(mobile);
        student.setAddress(address);
        studentService.save(student);
        return new ModelAndView("redirect:/student/lista");
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id")int id){
        if(studentService.existsById(id)){
            studentService.delete(id);
            return new ModelAndView("redirect:/student/lista");
        }
        return null;
    }

}
