package com.tablero.tablero.controllers;

import com.tablero.tablero.domains.Persona;
import com.tablero.tablero.domains.User;
import com.tablero.tablero.services.PersonaService;
import com.tablero.tablero.services.UserDetailsServiceImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/private")
public class PersonaController {

    private final PersonaService personaService;
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private BCryptPasswordEncoder encoder;

    public PersonaController(PersonaService personaService, UserDetailsServiceImpl userDetailsServiceImpl) {
        this.personaService = personaService;
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        encoder = new BCryptPasswordEncoder();
    }

    @RequestMapping("/personas")
    public String getPersonas(Model model){
        model.addAttribute("personas", personaService.findAll());
        return "personas";
    }

    @RequestMapping("/personas/nuevo-externo")
    public String formPersonaNueva(Model modelo){
        Persona persona = new Persona();
        modelo.addAttribute("persona", persona);
        return "crear_persona";
    }

    @PostMapping("/crearPersona")
    public String crearPersona(@ModelAttribute("persona") Persona persona, @RequestParam String userName, @RequestParam String clave, RedirectAttributes redirectAttributes){
        // 1 Cliente, 2 Empleado
        String rol;

        rol = persona.getTipo() == 1 ? "cliente" : "empleado";
        User usuario = new User();
        usuario.setUserName(userName);
        usuario.setClave(encoder.encode(clave));
        usuario.setRole(rol);

        userDetailsServiceImpl.crearUsuario(usuario);

        personaService.crearPersona(persona);
        redirectAttributes.addFlashAttribute("mensaje", "¡Persona creada exitosamente!");
        return "redirect:/private/home";
    }

}
