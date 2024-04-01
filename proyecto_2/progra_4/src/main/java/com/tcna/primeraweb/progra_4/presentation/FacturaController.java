package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
