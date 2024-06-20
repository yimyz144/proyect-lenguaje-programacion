package com.uph.sistemasproyect.config;


import com.uph.sistemasproyect.entity.Rol;
import com.uph.sistemasproyect.enums.RolNombre;
import com.uph.sistemasproyect.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
//        Rol rolAdmin = new Rol(RolNombre.ROLE_ADMIN);
//        Rol rolUser = new Rol(RolNombre.ROLE_USER);
//        rolService.save(rolAdmin);
//        rolService.save(rolUser);
    }
}
