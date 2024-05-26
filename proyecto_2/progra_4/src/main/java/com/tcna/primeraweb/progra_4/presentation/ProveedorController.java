package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ActividadEntity;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorActividad;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ActividadService;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import com.tcna.primeraweb.progra_4.service.HaciendaStub;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/ProveedorController")
public class ProveedorController {


    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ActividadService actividadService;


    @Autowired
    private ClienteService clienteService;

    @Autowired
    private HaciendaStub HaciendaStub;


    @GetMapping("/Listadeproveedores") // Añade esta línea para mapear el método a la URL
    public List<ProveedorEntity> listaProveedor() {
      List<ProveedorEntity> proveedores=  proveedorService.ObtenerProveedores();
      // Define el orden de los estados
      Map<String, Integer> estadoOrden = new HashMap<>();
      estadoOrden.put("Aceptado", 2);
      estadoOrden.put("Rechazado", 3);
      estadoOrden.put("En espera", 1);

      // Ordena la lista de proveedores según el estado
      proveedores.sort((p1, p2) -> {
        int orden1 = estadoOrden.getOrDefault(p1.getEstado(), 0);
        int orden2 = estadoOrden.getOrDefault(p2.getEstado(), 0);
        return Integer.compare(orden1, orden2);
      });
      return proveedores;
    }

    @PostMapping("NewProveedor")
public ResponseEntity<ProveedorEntity> crearProveedor(@RequestBody ProveedorActividad proveedorActividad) {
    try {
        ProveedorEntity proveedor = proveedorActividad.getProveedor();
        ActividadEntity actividad = proveedorActividad.getActividad();
        proveedor.setAdmin((byte) 0);
        proveedorService.crearProveedores(proveedor);
        actividadService.crear(actividad);
        return ResponseEntity.ok(proveedor);
    } catch (DataIntegrityViolationException e) {
        return ResponseEntity.badRequest().build();
    }
}


//    @PostMapping("NewProveedor")
//    public ResponseEntity<ProveedorEntity> crearProveedor(@RequestBody ProveedorEntity proveedor, @RequestBody ActividadEntity actividad) {
//        try {
//            proveedor.setAdmin((byte) 0);
//            proveedor.setEstado("En espera");
//            proveedorService.crearProveedores(proveedor);
//            actividadService.crear(actividad);
//            return ResponseEntity.ok(proveedor);
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    @PostMapping("/editar")
    public ResponseEntity<ProveedorEntity> actualizarProveedores(@RequestBody ProveedorEntity proveedor){
        try {
            if(proveedorService.actualizarProveedor(proveedor)){
                return ResponseEntity.ok(proveedor);
            }else {
                return ResponseEntity.badRequest().build();
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/NewActividad")
    public ResponseEntity<ActividadEntity> crearActividad(@RequestBody ActividadEntity actividad) {
        try {
            actividadService.crear(actividad);
            return ResponseEntity.ok(actividad);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/DeleteActividad")
    public ResponseEntity<ActividadEntity> eliminarActividad(@RequestBody ActividadEntity actividad) {
        try {
            if(actividadService.eliminar(actividad)) {
                return ResponseEntity.ok(actividad);
            }else {
                return ResponseEntity.badRequest().build();
            }
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Falba ver como se va a implementar en el frontend
//    @GetMapping("/estado")
//    public ResponseEntity<ProveedorEntity> cambiarEstado(@RequestBody ProveedorEntity p) {
//        ProveedorEntity proveedor = proveedorService.obtenerProveedorPorId(p.getIdProveedor());
//        if(estado.equals("Aceptado")){
//            proveedor.setEstado("Aceptado");
//            proveedorService.actualizarProveedor(proveedor);
//            return ResponseEntity.ok(proveedor);
//        }else if(estado.equals("Rechazado")){
//            proveedor.setEstado("Rechazado");
//            proveedorService.actualizarProveedor(proveedor);
//            return ResponseEntity.ok(proveedor);
//        }
//        return ResponseEntity.badRequest().build();
//    }












// Creo que no se ocupa
//    @GetMapping("/nuevo")
//   public String MostrarFormularioNuevoProveedor(Model model){
//        model.addAttribute("proveedor",new ProveedorEntity());
//        return "formularioproveedor";
//   }

//
//// Arreglar el registro de proveedores
//    @PostMapping("/nuevo_proveedor")
//    public void guardarNuevoProveedor(@RequestBody ProveedorEntity proveedor){
//
//        proveedor.setAdmin((byte) 0);
//        proveedor.setEstado("En espera");
//        proveedorService.crearProveedores(proveedor);
//
////        if ("Registrar".equals(proveedor.getTipoProveedor())) {
////            /*redirectAttributes.addFlashAttribute("error", "Por favor, seleccione un tipo de proveedor válido (Físico o Jurídico)");*/
////            model.addAttribute("proveedor", proveedor);
////            model.addAttribute("mensaje", "Seleccione un tipo de proveedor");
////            return "redirect:/ProveedorController/nuevo";
////        }
////
////        if ("Registrar".equals(proveedor.getActividadComercial())) {
////           /* redirectAttributes.addFlashAttribute("error", "Por favor, seleccione una actividad comercial válida (Servicios, Consumibles, Infraestructura, Bienes)");*/
////            model.addAttribute("proveedor", proveedor);
////            model.addAttribute("alerta", "Seleccione una actividad comercial");
////            return "redirect:/ProveedorController/nuevo";
////        }
////
////        if ("Físico".equals(proveedor.getTipoProveedor()) && proveedor.getIdProveedor().length() != 9) {
////            model.addAttribute("error", "El número de identificación de un cliente físico debe tener exactamente 9 dígitos.");
////            return "redirect:/ProveedorController/nuevo";
////        }
////
////        if ("Jurídico".equals(proveedor.getTipoProveedor()) && proveedor.getIdProveedor().length() != 10) {
////            model.addAttribute("mensaj", "El número de identificación de un cliente jurídico debe tener exactamente 10 dígitos.");
////            return "redirect:/ProveedorController/nuevo";
////        }
////
////        if (!proveedor.getNombre().matches("[a-zA-Z ]+")) {
////            model.addAttribute("erro", "El nombre solo puede contener letras y espacios.");
////            return "redirect:/ProveedorController/nuevo";
////        }
////        if(proveedorService.obtenerProveedorPorId(proveedor.getIdProveedor())==null){
////            if (HaciendaStub.validarRegistroProveedor(proveedor)) {
////                proveedor.setAdmin((byte) 0);
////                proveedor.setEstado("En espera");
////                proveedorService.crearProveedores(proveedor);
////            } else {
////
////            }
////        }else {
////        }
//    }
//



//----------------------------------------------------------------------------------------
    //Revisar
//    @GetMapping("/editar/{id}")
//    public String mostrarFormularioEditarPersona(@PathVariable String id, @ModelAttribute ProveedorEntity proveedor,Model model, HttpSession session){
//        String ID = (String) session.getAttribute("id_proveedor");
//        proveedor = proveedorService.obtenerProveedorPorId(ID);
//        model.addAttribute("id_proveedor",ID);
//        model.addAttribute("proveedor",proveedor);
//        model.addAttribute("editar_PROVEDOR","/ProveedorController/editar"+id);
//
//
//        return "FormularioEditarProveedor";
//    }
//
//
//





    //---------------------------------------------------------------

    // Creo que no se ocupa
//    @PostMapping("/crear-cliente")
//    public String crearCliente(@RequestParam("nombre") String nombre,
//                               @RequestParam("direccion") String direccion,
//                               @RequestParam("correoElectronico") String correoElectronico,
//                               @RequestParam("proveedorId") String proveedorId,Model model) {
//
//
//        ProveedorEntity proveedor = proveedorService.obtenerProveedorPorId(proveedorId);
//
//        if (proveedor != null) {
//
//            ClienteEntity nuevoCliente = new ClienteEntity();
//            nuevoCliente.setNombre(nombre);
//            nuevoCliente.setDireccion(direccion);
//            nuevoCliente.setCorreoElectronico(correoElectronico);
//            nuevoCliente.setProveedorId(proveedor.getIdProveedor());
//
//
//            clienteService.crearCliente(nuevoCliente);
//
//            return "redirect:/cliente-creado";
//        } else {
//
//            model.addAttribute("ERROR","proveedor no exista");
//            return "index";
//        }
//    }


}
