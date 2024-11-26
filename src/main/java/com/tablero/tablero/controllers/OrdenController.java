package com.tablero.tablero.controllers;

import com.tablero.tablero.domains.Orden;
import com.tablero.tablero.domains.Persona;
import com.tablero.tablero.services.OrdenService;
import com.tablero.tablero.services.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/private")
public class OrdenController {

    private final OrdenService ordenService;
    private final PersonaService personaService;

    public OrdenController(OrdenService ordenService, PersonaService personaService) {
        this.ordenService = ordenService;
        this.personaService = personaService;
    }

    @RequestMapping("/ordenesCliente")
    public String getOrdenesCliente(@RequestParam("id") Long id, Model modelo){
        List<Orden> ordenes = ordenService.findByClienteId(id);
        Persona persona = personaService.findById(id).get();
        modelo.addAttribute("ordenes",ordenes);
        modelo.addAttribute("persona",persona);
        return "ordenes_pedidas";
    }

    @RequestMapping("/ordenesEmpleado")
    public String getOrdenesEmpleado(@RequestParam("id") Long id, Model modelo){
        List<Orden> ordenes = ordenService.findByEncargadoId(id);
        Persona persona = personaService.findById(id).get();
        modelo.addAttribute("ordenes", ordenes);
        modelo.addAttribute("persona", persona);
        return "ordenes_encargadas";
    }

    @RequestMapping("/ordenes")
    public String getOrdenes(Model modelo){
        modelo.addAttribute("ordenes", ordenService.findAll());
        return "ordenes";
    }

    @RequestMapping("/ordenes/nueva")
    public String formOrdenNueva(Model modelo){
        List<Persona> clientes = (List<Persona>) personaService.findByTipo(1);
        List<Persona> empleados = (List<Persona>) personaService.findByTipo(2);
        modelo.addAttribute("clientes", clientes);
        modelo.addAttribute("empleados", empleados);
        Orden orden = new Orden();
        modelo.addAttribute("orden", orden);
        return "crear_orden";
    }

    @PostMapping("/crearOrden")
    public String crearOrden(@RequestParam("archivo") MultipartFile file, @ModelAttribute("orden") Orden orden, RedirectAttributes redirectAttributes){
        // Establece la fecha de registro al momento de crear la orden
        orden.setFechaRegistro(LocalDate.now());
        if (!file.isEmpty()) {
            // Verificar el tipo MIME del archivo
            String tipoArchivo = file.getContentType();

            if (tipoArchivo == null || !tipoArchivo.equals("application/pdf")) {
                redirectAttributes.addFlashAttribute("error", "Solo se permiten archivos PDF");
                return "redirect:/home";
            }
            try {
                orden.setData(file.getBytes());  // Guardamos el archivo como un arreglo de bytes

                // Agregamos un mensaje para mostrar en la vista
                System.out.println("Archivo cargado");;
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al cargar el archivo");;
            }
        }

        ordenService.crearOrden(orden);
        redirectAttributes.addFlashAttribute("mensaje", "¡Orden creada exitosamente!");
        return "redirect:/home";
    }

    @RequestMapping("/descargarAdjunto/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> descargarAdjunto(@PathVariable Long id) {
        Orden orden = ordenService.findById(id).orElse(null);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + orden.getId()); // El nombre del archivo será el original

        // Devolvemos los datos del archivo como bytes, con el tipo MIME de PDF
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(orden.getData());
    }

    //Método para cambiar de estado una orden
    @RequestMapping("/cambiarEstado")
    public String cambiarEstado(@RequestParam("id") Long id, @RequestParam("estado") int estado, Model modelo, RedirectAttributes redirectAttributes){
        ordenService.cambiarEstado(id, estado);
        redirectAttributes.addFlashAttribute("mensaje", "¡Estado actualizado!");
        Persona encargado = ordenService.findById(id).get().getEncargado();
        List<Orden> ordenes = ordenService.findByEncargadoId(encargado.getId());
        modelo.addAttribute("ordenes", ordenes);
        modelo.addAttribute("persona", encargado);
        return "ordenes_encargadas";
    }

}
