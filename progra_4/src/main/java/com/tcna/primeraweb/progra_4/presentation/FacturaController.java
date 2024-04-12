package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/FacturaController")
public class FacturaController {
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;


    @GetMapping("/ListadeFacturas") // Añade esta línea para mapear el método a la URL
    public String listarFacturas(Model model, HttpSession session) {
        // Verificar si hay facturas asociadas al proveedor en la sesión
        List<ClienteEntity> clientesProveedor = (List<ClienteEntity>) session.getAttribute("clientes");
        if (clientesProveedor != null) {
            // Si hay clientes asociados al proveedor en la sesión, agregarlos al modelo
            model.addAttribute("clientesProveedor", clientesProveedor);
        }
        List<FacturaEntity> facturasProveedor = (List<FacturaEntity>) session.getAttribute("facturas");
        if (facturasProveedor != null) {
            // Si hay facturas asociadas al proveedor en la sesión, agregarlos al modelo
            model.addAttribute("facturasProveedor", facturasProveedor);
        }
        // Obtener todas las facturas y agregarlos al modelo
        List<FacturaEntity> facturas = facturaService.ObtenerFacturas();
        model.addAttribute("facturas", facturas);
        return "listarFacturas"; // Suponiendo que el nombre de la vista es listarFacturas.html
    }
    @GetMapping("/NuevaFactura") // Añade esta línea para mapear el método a la URL
    public String nuevaFactura(Model model, HttpSession session) {
        List<ClienteEntity> clientesProveedor = (List<ClienteEntity>) session.getAttribute("clientes");
        if (clientesProveedor != null) {
            // Si hay clientes asociados al proveedor en la sesión, agregarlos al modelo
            model.addAttribute("clientesProveedor", clientesProveedor);
        }
        model.addAttribute("factura", new FacturaEntity());
        model.addAttribute("accion","/FacturaController/guardarFactura");
        return "formularioFactura";
    }

    @PostMapping("/guardarFactura") // Añade esta línea para mapear el método a la URL
    public String guardarFactura(FacturaEntity factura, HttpSession session) {
        facturaService.crearFactura(factura);
        return "redirect:/FacturaController/ListadeFacturas"; // Redirigir a la lista de facturas
    }

}
