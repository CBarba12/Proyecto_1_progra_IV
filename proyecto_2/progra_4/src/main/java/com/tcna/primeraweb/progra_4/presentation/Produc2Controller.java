package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/Producto2Controller")
public class Produc2Controller {

    @Autowired
    private ProveedorService proveedorService;
    //@Autowired
   // private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;

    @GetMapping("/listar-Producto")
    public List<ProductoEntity> ver(){
        List<ProductoEntity> proveedores=  productoService.ObtenerProductos();
        return proveedores;
    }



    @PostMapping("/Guardar-Producto")
    public ProductoEntity crear(@RequestBody ProductoEntity p) {
        return productoService.crearProductos(p);
    }


}
