package com.tcna.primeraweb.progra_4.presentation;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
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
        List<ProductoEntity> productos = productoService.obtenerProductoPorProveedor(ID);


        // Crear un mapa de nombres de productos
       Map<String, String> nombresProductos = productos.stream()
        .collect(Collectors.toMap(producto -> String.valueOf(producto.getProductoId()), ProductoEntity::getNombre));

        // Crear un mapa de nombres de productos
        Map<String, Double> preciosProductos = productos.stream()
                .collect(Collectors.toMap(producto -> String.valueOf(producto.getProductoId()), ProductoEntity::getPrecio));

        if (!filteredFacturas.isEmpty() && !productos.isEmpty()) {
            model.addAttribute("Facturas", filteredFacturas);
            model.addAttribute("Productos", productos);
            model.addAttribute("nombresProductos", nombresProductos);
            model.addAttribute("preciosProductos", preciosProductos);
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
    public String guardarFactura(@RequestParam("clienteId") String clienteId, @RequestParam("productoId") String productoId, @RequestParam("cantidad") String cantidad, Model model, HttpSession session) {
        String ID= (String) session.getAttribute("id_proveedor");
        List<ProductoEntity> productos = productoService.obtenerProductoPorProveedor(ID);
        List<ClienteEntity> clientesProveedor = clienteService.obtenerClientesPorProveedor(ID);

        ProductoEntity producto = productos.stream()
            .filter(p -> p.getProductoId() == Integer.parseInt(productoId))
            .findFirst()
            .orElse(null);

        ClienteEntity cliente = clientesProveedor.stream()
            .filter(c -> c.getClienteId().equals(clienteId))
            .findFirst()
            .orElse(null);

        if (producto != null && cliente != null) {
            FacturaEntity factura = new FacturaEntity();
            LocalDate fecha = LocalDate.now();
            factura.setProveedor(ID);
            factura.setCliente(cliente.getClienteId());
            factura.setId_producto(producto.getProductoId());
            factura.setFecha(Date.valueOf(fecha));
            factura.setCantidad(Integer.parseInt(cantidad));
            factura.setTotal(producto.getPrecio() * factura.getCantidad());
            facturaService.crearFactura(factura);
        }

        model.addAttribute("id_proveedor",ID);
        return "redirect:/FacturaController/ListadeFacturas"; // Redirigir a la lista de facturas
    }

    @GetMapping("/pdf/{id}")
    public void pdf(@PathVariable("id") String facturaID, Model model, HttpSession session, HttpServletResponse response) {
        String ID= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",ID);
        List<ProductoEntity> productos = productoService.obtenerProductoPorProveedor(ID);
        List<ClienteEntity> clientesProveedor = clienteService.obtenerClientesPorProveedor(ID);
        List<FacturaEntity> facturaEntities = facturaService.ObtenerFacturas();


        List<FacturaEntity> filteredFacturas = facturaEntities.stream()
                .filter(factura -> factura.getProveedor().equals(ID))
                .collect(Collectors.toList());
        FacturaEntity factura = filteredFacturas.stream()
                .filter(f -> f.getFacturaId() == Integer.parseInt(facturaID))
                .findFirst()
                .orElse(null);



        response.setContentType("application/pdf");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("Factura ID: " + factura.getFacturaId()));
            document.add(new Paragraph("Proveedor: " + factura.getProveedor()));
            document.add(new Paragraph("Cliente: " + factura.getCliente() + "  Nombre: " +
                    clientesProveedor.stream().filter(c -> c.getClienteId().equals(factura.getCliente())).findFirst().orElse(null).getNombre() + "  Correo: " +
                    clientesProveedor.stream().filter(c -> c.getClienteId().equals(factura.getCliente())).findFirst().orElse(null).getCorreoElectronico()));
            document.add(new Paragraph("Producto ID: " + factura.getId_producto() + "  Nombre: " +
                    productos.stream().filter(p -> p.getProductoId() == factura.getId_producto()).findFirst().orElse(null).getNombre() + "  Precio: " +
                    productos.stream().filter(p -> p.getProductoId() == factura.getId_producto()).findFirst().orElse(null).getPrecio()));
            document.add(new Paragraph("Cantidad: " + factura.getCantidad()));
            document.add(new Paragraph("Total: " + factura.getTotal()));
            document.add(new Paragraph("Fecha: " + factura.getFecha()));
            document.add(new Paragraph("\n\n"));

            document.close();
        } catch (DocumentException | IOException ex) {
            throw new RuntimeException("Error al generar el PDF", ex);
        }
    }

    @GetMapping("/pdf")
    public void pdf(Model model, HttpSession session, HttpServletResponse response) {
        String ID= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",ID);
        List<ProductoEntity> productos = productoService.obtenerProductoPorProveedor(ID);
        List<ClienteEntity> clientesProveedor = clienteService.obtenerClientesPorProveedor(ID);
        List<FacturaEntity> facturaEntities = facturaService.ObtenerFacturas();

        List<FacturaEntity> filteredFacturas = facturaEntities.stream()
                .filter(factura -> factura.getProveedor().equals(ID))
                .collect(Collectors.toList());

        response.setContentType("application/pdf");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            for (FacturaEntity factura : filteredFacturas) {
                document.add(new Paragraph("Factura ID: " + factura.getFacturaId()));
                document.add(new Paragraph("Proveedor: " + factura.getProveedor()));
                document.add(new Paragraph("Cliente: " + factura.getCliente() + "  Nombre: " +
                        clientesProveedor.stream().filter(c -> c.getClienteId().equals(factura.getCliente())).findFirst().orElse(null).getNombre() + "  Correo: " +
                        clientesProveedor.stream().filter(c -> c.getClienteId().equals(factura.getCliente())).findFirst().orElse(null).getCorreoElectronico()));
                document.add(new Paragraph("Producto ID: " + factura.getId_producto() + "  Nombre: " +
                        productos.stream().filter(p -> p.getProductoId() == factura.getId_producto()).findFirst().orElse(null).getNombre() + "  Precio: " +
                        productos.stream().filter(p -> p.getProductoId() == factura.getId_producto()).findFirst().orElse(null).getPrecio()));
                document.add(new Paragraph("Cantidad: " + factura.getCantidad()));
                document.add(new Paragraph("Total: " + factura.getTotal()));
                document.add(new Paragraph("Fecha: " + factura.getFecha()));
                document.add(new Paragraph("\n\n"));
            }

            document.close();
        } catch (DocumentException | IOException ex) {
            throw new RuntimeException("Error al generar el PDF", ex);
        }
    }
    @GetMapping("/xml")
    public String xml(Model model, HttpSession session) {
        String ID= (String) session.getAttribute("id_proveedor");
        model.addAttribute("id_proveedor",ID);
        return "redirect:/FacturaController/ListadeFacturas";
    }

}
