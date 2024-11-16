package com.tablero.tablero.controllers;

import com.tablero.tablero.domains.Orden;
import com.tablero.tablero.services.OrdenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrdenController {

    private final OrdenService ordenService;

    public OrdenController(OrdenService ordenService) {
        this.ordenService = ordenService;
    }

    @RequestMapping("/ordenes/nueva")
    public String formOrdenNueva(Model modelo){
        Orden orden = new Orden();
        modelo.addAttribute("orden", orden);
        return "crear_orden";
    }

    @PostMapping("/crearOrden")
    public String crearOrden(@ModelAttribute("orden") Orden orden, RedirectAttributes redirectAttributes){
        ordenService.crearOrden(orden);
        redirectAttributes.addFlashAttribute("mensaje", "¡Orden creada exitosamente!");
        return "redirect:/home";
    }

}
