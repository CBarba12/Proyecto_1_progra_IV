package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ActividadEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ActividadService;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import com.tcna.primeraweb.progra_4.service.HaciendaStub;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/NewProveedor")
    public ResponseEntity<ProveedorEntity> crearProveedor(@RequestBody ProveedorEntity proveedor) {
        try {
            proveedor.setAdmin((byte) 0);
            proveedor.setEstado("En espera");
            proveedorService.crearProveedores(proveedor);
            proveedor.setContrasena("");
            return ResponseEntity.ok(proveedor);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/editar")
    public ResponseEntity<ProveedorEntity> actualizarProveedores(@RequestBody ProveedorEntity proveedor){
        try {
            if(proveedor != null && proveedorService.existeProveedor(proveedor.getIdProveedor())){
                 proveedor =  proveedorService.actualizarProveedor(proveedor);
                proveedor.setContrasena("");
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

    @PostMapping("/estado")
    public ResponseEntity<ProveedorEntity> cambiarEstado(@RequestBody ProveedorEntity p) {
        if(p != null && proveedorService.existeProveedor(p.getIdProveedor())) {
            p = proveedorService.actualizarProveedor(p);
            p.setContrasena("");
            return ResponseEntity.ok(p);
        }
        return ResponseEntity.badRequest().build();
    }
}
