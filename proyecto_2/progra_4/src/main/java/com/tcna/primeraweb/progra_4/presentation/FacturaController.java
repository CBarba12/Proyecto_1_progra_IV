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
import java.util.stream.Collectors;

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
        String ID= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",ID);
        List<FacturaEntity> facturaEntities = facturaService.ObtenerFacturas();
        List<FacturaEntity> filteredFacturas = facturaEntities.stream()
                .filter(factura -> factura.getProveedor().equals(ID))
                .collect(Collectors.toList());

        if (!filteredFacturas.isEmpty()) {
            model.addAttribute("Facturas", filteredFacturas);
        }

        return "listarFacturas"; // Suponiendo que el nombre de la vista es listarFacturas.html
    }


    @GetMapping("/NuevaFactura") // Añade esta línea para mapear el método a la URL
    public String nuevaFactura(Model model, HttpSession session) {
        String ID= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",ID);

        List<ClienteEntity> clientesProveedor = clienteService.obtenerClientesPorProveedor(ID);


        List<ProductoEntity> productos = productoService.obtenerProductoPorProveedor(ID);


        model.addAttribute("clientes", clientesProveedor);
        model.addAttribute("productos", productos);


        return "formularioFactura";
    }

    @PostMapping("/guardarFactura") // Añade esta línea para mapear el método a la URL
    public String guardarFactura(FacturaEntity factura, HttpSession session) {



        return "redirect:/FacturaController/ListadeFacturas"; // Redirigir a la lista de facturas
    }

    @GetMapping("/pdf")
    public String pdf(Model model, HttpSession session) {
        String ID= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",ID);
        return "redirect:/FacturaController/ListadeFacturas";
    }
    @GetMapping("/xml")
    public String xml(Model model, HttpSession session) {
        String ID= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",ID);
        return "redirect:/FacturaController/ListadeFacturas";
    }

}
