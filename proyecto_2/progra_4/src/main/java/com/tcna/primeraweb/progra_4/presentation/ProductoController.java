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





    @GetMapping("/ListadeProducto") // Añade esta línea para mapear el método a la URL
    public List<ProductoEntity> listarProducto(@RequestBody ProveedorEntity proveedor) {
        List<ProductoEntity> ProductoProveedor =productoService.obtenerProductoPorProveedor(proveedor.getIdProveedor());
        if (ProductoProveedor != null) {
            return ProductoProveedor;
        }
        return null;
    }



//    // Eliminar
//    @GetMapping("/nuevoProducto")
//    public String MostrarFormularioNuevoCliente(HttpSession session, Model model){
//
//        ProductoEntity cle=new ProductoEntity();
//        String ID= (String) session.getAttribute("id_proveedor");
//        String actividadComercial = (String) proveedorService.obtenerProveedorPorId(ID).getActividadComercial();
//        model.addAttribute("actividad",actividadComercial);
//        model.addAttribute("id_proveedor",ID);
//        cle.setProveedorId(ID);
//
//        model.addAttribute("producto",cle);
//
//        return "formularioProducto";
//    }


    @PostMapping("/NewProducto")
    public ResponseEntity<ProductoEntity> guardarNuevoCliente(@RequestBody ProductoEntity producto){
        if(producto != null){
            productoService.crearProductos(producto);
            return ResponseEntity.ok(producto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/editarProducto/{id}")
    public String editarProducto(@PathVariable("id") String id, Model model, HttpSession session) {
        List<ProductoEntity> productos = productoService.ObtenerProductos();

        ProductoEntity producto = productos.stream().filter(p -> p.getProductoId() == Integer.parseInt(id)).findFirst().orElse(null);
        String Provedor= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",Provedor);
        if (producto != null) {

            model.addAttribute("producto", producto);

            return "formularioEditarProducto";
        } else {
            return "redirect:/ProductoController/ListadeProducto";
        }
    }

    @PostMapping("/edit")
    public String guardarEditarProducto(@ModelAttribute ProductoEntity producto, Model model, HttpSession session){
        String Provedor= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",Provedor);
        producto.setProveedorId(Provedor);
        productoService.actualizarProducto(producto);
        return "redirect:/ProductoController/ListadeProducto";
    }

    @GetMapping("/eliminarProducto/{id}")
    public String eliminarProducto(@PathVariable("id") String id, Model model, HttpSession session) {
        String Provedor= (String) session.getAttribute("id_proveedor");
        List<ProductoEntity> productos = productoService.ObtenerProductos();
        ProductoEntity producto = productos.stream().filter(p -> p.getProductoId() == Integer.parseInt(id)).findFirst().orElse(null);
        model.addAttribute("id_proveedor",Provedor);
        productoService.eliminarProducto(Integer.parseInt(id));
        return "redirect:/ProductoController/ListadeProducto";
    }

}
