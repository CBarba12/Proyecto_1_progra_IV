package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/ProveedorController")
public class ProveedorController {


    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;


    @GetMapping("/Listadeproveedores") // Añade esta línea para mapear el método a la URL
    public String listaProveedor(Model model){

        List<ProveedorEntity> proveedores=  proveedorService.ObtenerProveedores();
   
        model.addAttribute("listaProveedor", proveedores);
        return "listarproveedor";
    }





    @GetMapping("/nuevo")
   public String MostrarFormularioNuevoProveedor(Model model){
        model.addAttribute("proveedor",new ProveedorEntity());
        model.addAttribute("accion","/ProveedorController/nuevo");
        return "formularioproveedor";
   }


    /*@PostMapping("/nuevo")
   public String guardarNuevoProveedor(@ModelAttribute ProveedorEntity proveedor, Model model){
        String idProveedor = proveedor.getIdProveedor();
        String tipoProveedor = proveedor.getTipoProveedor();

        if ("fisico".equalsIgnoreCase(tipoProveedor) && idProveedor.length() != 9) {
            model.addAttribute("error", "El número de identificación de un proveedor físico debe tener 9 dígitos.");
            return "formularioproveedor";
        }

        if ("juridico".equalsIgnoreCase(tipoProveedor) && idProveedor.length() != 10) {
            model.addAttribute("error", "El número de identificación de un proveedor jurídico debe tener 10 dígitos.");
            return "formularioproveedor";
        }

        proveedor.setActivo(true);
        proveedorService.crearProveedores(proveedor);

        return "redirect:/ProveedorController/Listadeproveedores";
   }*/

    @PostMapping("/nuevo")
    public String guardarNuevoProveedor(@ModelAttribute ProveedorEntity proveedor){
        proveedor.setActivo(true);
        proveedorService.crearProveedores(proveedor);

        return "redirect:/ProveedorController/Listadeproveedores";
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
     proveedorService.actualizarProveedro(id,proveedor);

     return "redirect:/ProveedorController/Listadeproveedores";
    }


    //---------------------------------------------------------------






    @GetMapping("/eliminar/{id}")
    public String eliminarProveedor(@PathVariable String id, Model model){


        try {
            proveedorService.eliminarProveedor(id);
            return "redirect:/ProveedorController/Listadeproveedores";
        } catch (DataIntegrityViolationException e) {
            // Captura la excepción y agrega un mensaje de error al modelo
            model.addAttribute("error", "No se puede eliminar este proveedor porque hay clientes asociados a él.");
            return "redirect:/ProveedorController/Listadeproveedores";
        }
    }




    @PostMapping("/crear-cliente")
    public String crearCliente(@RequestParam("nombre") String nombre,
                               @RequestParam("direccion") String direccion,
                               @RequestParam("correoElectronico") String correoElectronico,
                               @RequestParam("proveedorId") String proveedorId) {

        // Obtener el proveedor asociado al ID proporcionado
        ProveedorEntity proveedor = proveedorService.obtenerProveedorPorId(proveedorId);

        // Verificar si el proveedor existe
        if (proveedor != null) {
            // Crear una nueva instancia de ClienteEntity
            ClienteEntity nuevoCliente = new ClienteEntity();
            nuevoCliente.setNombre(nombre);
            nuevoCliente.setDireccion(direccion);
            nuevoCliente.setCorreo_electronico(correoElectronico);
            // Establecer la relación con el proveedor
            nuevoCliente.setProveedor_id(proveedor.getIdProveedor());

            // Guardar el cliente en la base de datos
            clienteService.crearCliente(nuevoCliente);

            // Redireccionar a la página de éxito o a donde desees
            return "redirect:/cliente-creado";
        } else {
            // Manejar el caso en el que el proveedor no exista
            return "redirect:/error-crear-cliente";
        }
    }



}
