package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
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





    @GetMapping("/ListadeProducto") // Añade esta línea para mapear el método a la URL
    public String listarProducto(Model model, HttpSession session) {

        String ID= (String) session.getAttribute("id_proveedor");

        List<ProductoEntity> ProductoProveedor =productoService.obtenerProductoPorProveedor(ID);


        if (ProductoProveedor != null) {
            model.addAttribute("ProductoProveedor", ProductoProveedor);
        }


        return "listarProductos";
    }




    @GetMapping("/nuevoProducto")
    public String MostrarFormularioNuevoCliente(@RequestParam("proveedorId") String numeroIdentificacion, Model model){

        ProductoEntity cle=new ProductoEntity();
        cle.setProveedorId(numeroIdentificacion);

        model.addAttribute("producto",cle);

        return "formularioProducto";
    }


    @PostMapping("/nuevo")
    public String guardarNuevoCliente(@ModelAttribute ProductoEntity producto, HttpSession session, Model model){

        String Provedor= (String) session.getAttribute("id_proveedor");

        producto.setProveedorId(Provedor);
        productoService.crearProductos(producto);

        List<ProductoEntity> ProductoProveedor =productoService. obtenerProductoPorProveedor(Provedor);

        if (ProductoProveedor != null) {
            model.addAttribute("ProductoProveedor", ProductoProveedor);
        }


        return "listarProductos";
    }





}
