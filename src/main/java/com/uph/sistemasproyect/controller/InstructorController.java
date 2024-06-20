package com.uph.sistemasproyect.controller;

import com.uph.sistemasproyect.entity.Address;
import com.uph.sistemasproyect.entity.Course;
import com.uph.sistemasproyect.entity.Instructor;
import com.uph.sistemasproyect.entity.Student;
import com.uph.sistemasproyect.service.CourseService;
import com.uph.sistemasproyect.service.InstructorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
    @Autowired
    private InstructorService instructorService;


    @GetMapping("lista")
    public ModelAndView list() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/instructor/lista");
        List<Instructor> instructors = instructorService.getListAll();
        mv.addObject("instructors", instructors);
        return mv;
    }

    @GetMapping("nuevo")
    public String nuevo() {
        return "instructor/nuevo";
    }

    @PostMapping("/guardar")
    public ModelAndView crear(@RequestParam String legalIdentifier,
                              @RequestParam String name,
                              @RequestParam String surname,
                              @RequestParam String email,
                              @RequestParam String mobile,
                              @RequestParam String profession,
                              @RequestParam String street,
                              @RequestParam String numberStreet,
                              @RequestParam String province,
                              @RequestParam String city,
                              @RequestParam String codePostal
    ) {

        ModelAndView mv = new ModelAndView();
        Instructor instructor = new Instructor();
        Address address = new Address();
        address.setStreet(street);
        address.setNumberStreet(numberStreet);
        address.setProvince(province);
        address.setCity(city);
        address.setCodePostal(codePostal);
        instructor.setLegalIdentifier(legalIdentifier);
        instructor.setName(name);
        instructor.setSurname(surname);
        instructor.setEmail(email);
        instructor.setMobile(mobile);
        instructor.setProfession(profession);
        instructor.setAddress(address);
        if (StringUtils.isBlank(legalIdentifier)) {
            mv.setViewName("instructor/nuevo");
            mv.addObject("error", "El Dni/Nie no puede estar vacío");
            return mv;
        }
        if (instructorService.existsByDni(legalIdentifier)) {
            mv.setViewName("instructor/nuevo");
            mv.addObject("error", "El Dni/Nie  ya existe");
            return mv;
        }
        instructorService.save(instructor);
        mv.setViewName("redirect:/instructor/lista");
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") int id) {
        if (!instructorService.existById(id))
            return new ModelAndView("redirect:/instructor/lista");
        Instructor instructor = instructorService.getById(id).get();
        ModelAndView mv = new ModelAndView("/instructor/editar");
        mv.addObject("instructor", instructor);
        return mv;
    }

    @PostMapping("/actualizar")
    public ModelAndView actualizar(
            @RequestParam int id,
            @RequestParam String email,
            @RequestParam String mobile,
            @RequestParam String street,
            @RequestParam String numberStreet,
            @RequestParam String province,
            @RequestParam String city,
            @RequestParam String codePostal) {
        if (!instructorService.existById(id))
            return new ModelAndView("redirect:/instructor/lista");
        ModelAndView mv = new ModelAndView();
        Instructor instructor = instructorService.getById(id).get();
        if (StringUtils.isBlank(email) || StringUtils.isBlank(mobile) || StringUtils.isBlank(street) || StringUtils.isBlank(numberStreet)
                || StringUtils.isBlank(province) || StringUtils.isBlank(city) || StringUtils.isBlank(codePostal)) {
            mv.setViewName("instructor/editar");
            mv.addObject("instructor", instructor);
            mv.addObject("error", "Los campos no pueden estar vacios");
            return mv;
        }

        Address address = instructor.getAddress();
        address.setStreet(street);
        address.setNumberStreet(numberStreet);
        address.setProvince(province);
        address.setCity(city);
        address.setCodePostal(codePostal);
        instructor.setEmail(email);
        instructor.setMobile(mobile);
        instructor.setAddress(address);
        instructorService.save(instructor);
        return new ModelAndView("redirect:/instructor/lista");
    }

    @GetMapping("/borrar/{id}")
    public ModelAndView borrar(@PathVariable("id")int id){
        if(instructorService.existById(id)){
            instructorService.delete(id);
            return new ModelAndView("redirect:/instructor/lista");
        }
        return null;
    }
}
