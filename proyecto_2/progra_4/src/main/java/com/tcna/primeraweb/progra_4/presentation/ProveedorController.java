package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.tcna.primeraweb.progra_4.service.HaciendaStub;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ProveedorController")
public class ProveedorController {


    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private HaciendaStub HaciendaStub;

    @GetMapping("/Listadeproveedores") // Añade esta línea para mapear el método a la URL
    public String listaProveedor(Model model){

        List<ProveedorEntity> proveedores=  proveedorService.ObtenerProveedores();
        model.addAttribute("listaProveedor", proveedores);


        return "listarproveedor";
    }





    @GetMapping("/nuevo")
   public String MostrarFormularioNuevoProveedor(Model model){
        model.addAttribute("proveedor",new ProveedorEntity());
        return "formularioproveedor";
   }



    @PostMapping("/nuevo_proveedor")
    public String guardarNuevoProveedor(@ModelAttribute ProveedorEntity proveedor, RedirectAttributes redirectAttributes, Model model){

        // Verificar si el tipo de proveedor es "Registrar"
        if ("Registrar".equals(proveedor.getTipoProveedor())) {
            // El tipo de proveedor es "Registrar", no permitir guardar el proveedor
            redirectAttributes.addFlashAttribute("error", "Por favor, seleccione un tipo de proveedor válido (Físico o Jurídico)");
            model.addAttribute("proveedor", proveedor);
            return "formularioproveedor";
        }

        // Verificar si la actividad comercial es "Registrar"
        if ("Registrar".equals(proveedor.getActividadComercial())) {
            // La actividad comercial es "Registrar", no permitir guardar el proveedor
            redirectAttributes.addFlashAttribute("error", "Por favor, seleccione una actividad comercial válida (Servicios, Consumibles, Infraestructura, Bienes)");
            model.addAttribute("proveedor", proveedor);
            return "formularioproveedor";
        }

        if (HaciendaStub.validarRegistroProveedor(proveedor)) {
            // El proveedor no está registrado, permitir el registro
            proveedor.setEstado("En espera");
            proveedorService.crearProveedores(proveedor);
            return "redirect:/";
        } else {
            // El proveedor ya está registrado, mostrar un mensaje de error o redirigir a una página de error
            return "redirect:/";
        }
    }




//----------------------------------------------------------------------------------------
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarPersona(@PathVariable String id, @ModelAttribute ProveedorEntity proveedor,Model model){
        proveedor.setIdProveedor(id);
        model.addAttribute("proveedor",proveedor);
        model.addAttribute("editar_PROVEDOR","/ProveedorController/editar"+id);


        return "FormularioEditarProveedor";
    }




    @PostMapping("/editar/{id}")
    public String actualizarProveedores(@PathVariable String id, @ModelAttribute ProveedorEntity proveedor){
     proveedorService.actualizarProveedor(id,proveedor);
     return "redirect:/ProveedorController/Listadeproveedores";
    }



    //---------------------------------------------------------------


    @PostMapping("/crear-cliente")
    public String crearCliente(@RequestParam("nombre") String nombre,
                               @RequestParam("direccion") String direccion,
                               @RequestParam("correoElectronico") String correoElectronico,
                               @RequestParam("proveedorId") String proveedorId,Model model) {


        ProveedorEntity proveedor = proveedorService.obtenerProveedorPorId(proveedorId);

        if (proveedor != null) {

            ClienteEntity nuevoCliente = new ClienteEntity();
            nuevoCliente.setNombre(nombre);
            nuevoCliente.setDireccion(direccion);
            nuevoCliente.setCorreoElectronico(correoElectronico);
            nuevoCliente.setProveedorId(proveedor.getIdProveedor());


            clienteService.crearCliente(nuevoCliente);

            return "redirect:/cliente-creado";
        } else {

            model.addAttribute("ERROR","proveedor no exista");
            return "index";
        }
    }


}
