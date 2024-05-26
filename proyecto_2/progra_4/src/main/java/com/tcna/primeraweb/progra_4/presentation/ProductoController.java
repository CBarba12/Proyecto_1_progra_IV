package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/ProductoController")
public class ProductoController {

    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;


    @GetMapping("/ListadeProducto/{idproveedor}") // Añade esta línea para mapear el método a la URL
    public List<ProductoEntity> listarProducto(@PathVariable("idproveedor") String proveedor){
        List<ProductoEntity> ProductoProveedor =productoService.obtenerProductoPorProveedor(proveedor);
        if (ProductoProveedor != null) {
            return ProductoProveedor;
        }
        return null;
    }

    @PostMapping("/NewProducto")
    public ResponseEntity<ProductoEntity> guardarNuevoCliente(@RequestBody ProductoEntity producto){
        if(producto != null){
            productoService.crearProductos(producto);
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.badRequest().build();
    }


    @PostMapping("/edit")
    public ResponseEntity<ProductoEntity> guardarEditarProducto(@RequestBody ProductoEntity producto){
        if(producto != null) {
            if (productoService.actualizarProducto(producto)) {
                return ResponseEntity.ok(producto);
            }
        }else {
            System.out.println("Producto Null");
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/delete")
    public ResponseEntity<ProductoEntity> eliminarProducto(@RequestBody ProductoEntity producto){
        if(producto != null){
            productoService.eliminarProducto(producto.getProductoId());
            return ResponseEntity.ok(producto);
        }else {
            System.out.println("Producto Null");
        }
        return ResponseEntity.badRequest().build();
    }
//
//    // Rutas
//        private baseURL="http://localhost:8080/api/ProductoController";
//
//        // Listar productos
//        return this.httpClient.get<Producto[]>(`${this.baseURL}/ListadeProducto`);
//
//        // Crear producto
//        return this.httpClient.post(`${this.baseURL}/NewProducto`,producto);
//
//        // Editar producto
//       return this.httpClient.post(`${this.baseURL}/edit`,producto);
//
//        // Eliminar producto
//        return this.httpClient.post(`${this.baseURL}/delete`,producto);

}
