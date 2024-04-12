package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ProductoController")
public class ProductoController {

    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;






    @GetMapping("/Listadeproductos") // Añade esta línea para mapear el método a la URL
    public String listaProducto(Model model, HttpSession session){

        List<ProductoEntity> productos=  productoService.ObtenerProductos();

        // Verificar si hay clientes asociados al proveedor en la sesión
        List<ClienteEntity> clientesProveedor = (List<ClienteEntity>) session.getAttribute("clientes");
        if (clientesProveedor != null) {
            // Si hay clientes asociados al proveedor en la sesión, agregarlos al modelo
            model.addAttribute("clientesProveedor", clientesProveedor);
        }
        model.addAttribute("productos", productos);

        return "listarProductos";
    }

    @GetMapping("/NuevoProducto") // Añade esta línea para mapear el método a la URL
    public String nuevoProducto(Model model, HttpSession session){

        List<ClienteEntity> clientesProveedor = (List<ClienteEntity>) session.getAttribute("clientes");
        if (clientesProveedor != null) {
            // Si hay clientes asociados al proveedor en la sesión, agregarlos al modelo
            model.addAttribute("clientesProveedor", clientesProveedor);
        }

        model.addAttribute("producto", new ProductoEntity());
        model.addAttribute("accion","/ProductoController/guardarProducto");

        return "formularioProducto";
    }

    @PostMapping("/guardarProducto") // Añade esta línea para mapear el método a la URL
    public String guardarProducto(@ModelAttribute ProductoEntity producto, HttpSession session){


        productoService.crearProductos(producto);

        return "redirect:/ProductoController/Listadeproductos";
    }

}
