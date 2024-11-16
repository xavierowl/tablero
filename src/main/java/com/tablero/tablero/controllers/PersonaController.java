package com.tablero.tablero.controllers;

import com.tablero.tablero.domains.Persona;
import com.tablero.tablero.services.PersonaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @RequestMapping("/personas")
    public String getPersonas(Model model){
        model.addAttribute("personas", personaService.findAll());
        return "personas";
    }

    @RequestMapping("/personas/nuevo")
    public String formPersonaNueva(Model modelo){
        Persona persona = new Persona();
        modelo.addAttribute("persona", persona);
        return "crear_usuario";
    }

    @PostMapping("/crearPersona")
    public String crearPersona(@ModelAttribute("persona") Persona persona, RedirectAttributes redirectAttributes){
        personaService.crearPersona(persona);
        redirectAttributes.addFlashAttribute("mensaje", "¡Persona creada exitosamente!");
        return "redirect:/home";
    }

}
