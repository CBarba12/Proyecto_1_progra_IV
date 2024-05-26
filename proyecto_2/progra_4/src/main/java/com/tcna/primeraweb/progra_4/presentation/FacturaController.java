package com.tcna.primeraweb.progra_4.presentation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import com.tcna.primeraweb.progra_4.service.FacturaService;
import com.tcna.primeraweb.progra_4.service.ProductoService;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/FacturaController")
public class FacturaController {
    @Autowired
    private ProveedorService proveedorService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private FacturaService facturaService;


    Font fontheader = FontFactory.getFont(FontFactory.COURIER, 20, Font.BOLD, BaseColor.WHITE);
    Font fontfooter = FontFactory.getFont(FontFactory.COURIER, 10, Font.BOLD, BaseColor.WHITE);
    Font font = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, BaseColor.BLACK);

    // Cambiar por id url
    @GetMapping("/ListadeFacturas") // Añade esta línea para mapear el método a la URL
    public List<FacturaEntity> listarFacturas(@RequestBody ProveedorEntity proveedor) {
        List<FacturaEntity> facturaEntities = facturaService.ObtenerFacturas();
        List<FacturaEntity> filteredFacturas = facturaEntities.stream()
                .filter(factura -> factura.getProveedor().equals(proveedor.getIdProveedor()))
                .collect(Collectors.toList());
         return filteredFacturas; // Devolver la lista de facturas
    }

    @PostMapping("/NewFactura")
    public ResponseEntity<FacturaEntity> crearFactura(@RequestBody FacturaEntity factura) {
        LocalDate fecha = LocalDate.now();
        factura.setFecha(Date.valueOf(fecha));
    //    factura.setTotal(factura.getCantidad() * factura.getPrecio()); // Puede no ser necesario si ya viene correcto
        if(facturaService.crearFactura(factura)){
            return ResponseEntity.ok(factura);
        }
        return ResponseEntity.badRequest().build();
    }

    // PDF para una factura en especifico
    @GetMapping("/pdfFactura")
    public void pdf(@RequestBody FacturaEntity factura, HttpServletResponse response) {
        ClienteEntity cliente = clienteService.obtenerClientePorId(factura.getCliente());
        ProductoEntity producto = productoService.obtenerProductoPorId(factura.getId_producto());

        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura_" + factura.getFacturaId() + ".pdf");

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(new Paragraph("\n\n"));
            document.add(style(new Chunk("Reporte Sistema de facturacion"), fontheader));
            document.add(new Paragraph("------------------------------------------------------------------------", font));
            document.add(new Paragraph("Factura ID: " + factura.getFacturaId(), font));
            document.add(new Paragraph("Proveedor: " + factura.getProveedor(), font));
            document.add(new Paragraph("Cliente: " + factura.getCliente(), font));
            document.add(new Paragraph("    Nombre: " + cliente.getNombre(), font));
            document.add(new Paragraph("    Correo: " + cliente.getCorreoElectronico(), font));
            document.add(new Paragraph("Producto ID: " + factura.getId_producto(), font));
            document.add(new Paragraph("    Nombre: " + producto.getNombre(), font));
            document.add(new Paragraph("    Precio: " + producto.getPrecio(), font));
            document.add(new Paragraph("Cantidad: " + factura.getCantidad(), font));
            document.add(new Paragraph("Total: " + factura.getTotal(), font));
            document.add(new Paragraph("Fecha: " + factura.getFecha(), font));
            document.add(new Paragraph("------------------------------------------------------------------------", font));
            document.add(new Paragraph("\n\n"));
            document.add(style(new Chunk("Reporte Generado por: " + factura.getProveedor()), fontfooter));

            document.close();
        } catch (DocumentException | IOException ex) {
            throw new RuntimeException("Error al generar el PDF", ex);
        }
    }

    // PDF para todas las facturas de un proveedor
    @GetMapping("/pdfProveedor")
    public void pdf(@RequestBody ProveedorEntity proveedor ,HttpServletResponse response) {
        List<FacturaEntity> facturaEntities = facturaService.ObtenerFacturas();
        List<FacturaEntity> filteredFacturas = facturaEntities.stream()
                .filter(factura -> factura.getProveedor().equals(proveedor.getIdProveedor()))
                .collect(Collectors.toList());

        response.setContentType("application/pdf");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facturas_" + proveedor.getIdProveedor() + ".pdf");
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            document.add(style(new Chunk("Reporte Sistema de facturacion"), fontheader));
            for (FacturaEntity factura : filteredFacturas) {
                ClienteEntity cliente = clienteService.obtenerClientePorId(factura.getCliente());
                ProductoEntity producto = productoService.obtenerProductoPorId(factura.getId_producto());
                document.add(new Paragraph("\n\n"));
                document.add(new Paragraph("------------------------------------------------------------------------", font));
                document.add(new Paragraph("Factura ID: " + factura.getFacturaId(), font));
                document.add(new Paragraph("Proveedor: " + factura.getProveedor(), font));
                document.add(new Paragraph("Cliente: " + factura.getCliente(), font));
                document.add(new Paragraph("    Nombre: " + cliente.getNombre(), font));
                document.add(new Paragraph("    Correo: " + cliente.getCorreoElectronico(), font));
                document.add(new Paragraph("Producto ID: " + factura.getId_producto()));
                document.add(new Paragraph("        Nombre: " + producto.getNombre(), font));
                document.add(new Paragraph("        Precio: " + producto.getPrecio(), font));
                document.add(new Paragraph("Cantidad: " + factura.getCantidad(), font));
                document.add(new Paragraph("Total: " + factura.getTotal(), font));
                document.add(new Paragraph("Fecha: " + factura.getFecha(), font));
            }
            document.add(new Paragraph("------------------------------------------------------------------------", font));
            document.add(new Paragraph("\n\n"));
            document.add(style(new Chunk("Reporte Generado por: " + proveedor.getIdProveedor()), fontfooter));

            document.close();
        } catch (DocumentException | IOException ex) {
            throw new RuntimeException("Error al generar el PDF", ex);
        }
    }

    private PdfPTable style(Chunk c, Font font) {
        PdfPCell cell = new PdfPCell(new Phrase(c));
        cell.setBackgroundColor(BaseColor.BLUE);
        cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
        cell.setVerticalAlignment(com.itextpdf.text.Element.ALIGN_MIDDLE);
        cell.setFixedHeight(50); // puedes ajustar la altura según tus necesidades

        PdfPTable table = new PdfPTable(1);
        table.setWidthPercentage(100); // esto hará que la tabla ocupe todo el ancho de la página
        table.addCell(cell);
        return table;
    }












    // Creo que no es necesario
//    @GetMapping("/NuevaFactura") // Añade esta línea para mapear el método a la URL
//    public String nuevaFactura(Model model, HttpSession session) {
//        String ID= (String) session.getAttribute("id_proveedor");
//        model.addAttribute("id_proveedor",ID);
//
//        List<ClienteEntity> clientesProveedor = clienteService.obtenerClientesPorProveedor(ID);
//
//
//        List<ProductoEntity> productos = productoService.obtenerProductoPorProveedor(ID);
//
//
//        model.addAttribute("clientes", clientesProveedor);
//        model.addAttribute("productos", productos);
//
//
//        return "formularioFactura";
//    }






    // Va a quedar este metodo para guia por aquello de que se vaya a ocupar para la idea
//    @PostMapping("/guardarFactura") // Añade esta línea para mapear el método a la URL
//    public String guardarFactura(@RequestParam("clienteId") String clienteId, @RequestParam("productoId") String productoId, @RequestParam("cantidad") String cantidad, Model model, HttpSession session) {
//        String ID= (String) session.getAttribute("id_proveedor");
//        List<ProductoEntity> productos = productoService.obtenerProductoPorProveedor(ID);
//        List<ClienteEntity> clientesProveedor = clienteService.obtenerClientesPorProveedor(ID);
//
//        ProductoEntity producto = productos.stream()
//            .filter(p -> p.getProductoId() == Integer.parseInt(productoId))
//            .findFirst()
//            .orElse(null);
//
//        ClienteEntity cliente = clientesProveedor.stream()
//            .filter(c -> c.getClienteId().equals(clienteId))
//            .findFirst()
//            .orElse(null);
//
//        if (producto != null && cliente != null) {
//            FacturaEntity factura = new FacturaEntity();
//            LocalDate fecha = LocalDate.now();
//            factura.setProveedor(ID);
//            factura.setCliente(cliente.getClienteId());
//            factura.setId_producto(producto.getProductoId());
//            factura.setFecha(Date.valueOf(fecha));
//            factura.setCantidad(Integer.parseInt(cantidad));
//            factura.setTotal(producto.getPrecio() * factura.getCantidad());
//            facturaService.crearFactura(factura);
//        }
//
//        model.addAttribute("id_proveedor",ID);
//        return "redirect:/FacturaController/ListadeFacturas"; // Redirigir a la lista de facturas
//    }




//    @GetMapping("/xml/{id}")
//    public void xml(@PathVariable("id") String facturaID, Model model, HttpSession session, HttpServletResponse response) {
//        String ID= (String) session.getAttribute("id_proveedor");
//        model.addAttribute("id_proveedor",ID);
//
//        FacturaEntity factura = getFactura(ID, facturaID);
//        ProveedorEntity proveedor = proveedorService.obtenerProveedorPorId(ID);
//        ClienteEntity cliente = getCliente(factura);
//        ProductoEntity producto = getProducto(ID, factura);
//
//        try {
//            org.w3c.dom.Document document = createXmlDocument();
//
//            Element facturasxml = document.createElement("facturas");
//            Element facturaxml = createFacturaElement(document, factura, proveedor, cliente, producto);
//
//            facturasxml.appendChild(facturaxml);
//            document.getDocumentElement().appendChild(facturasxml);
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(document);
//
//            response.setContentType("application/xml");
//            response.setHeader("Content-Disposition", "attachment; filename=factura.xml");
//
//            transformer.transform(source, new StreamResult(response.getOutputStream()));
//
//        } catch (ParserConfigurationException | TransformerException | IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    @GetMapping("/xml")
//    public void xml(Model model, HttpSession session, HttpServletResponse response) {
//        String ID= (String) session.getAttribute("id_proveedor");
//        model.addAttribute("id_proveedor",ID);
//
//        List<FacturaEntity> facturaEntities = facturaService.ObtenerFacturas();
//        List<FacturaEntity> filteredFacturas = facturaEntities.stream()
//                .filter(factura -> factura.getProveedor().equals(ID))
//                .collect(Collectors.toList());
//
//        try {
//            org.w3c.dom.Document document = createXmlDocument();
//
//            Element facturasxml = document.createElement("facturas");
//
//            for (FacturaEntity factura : filteredFacturas) {
//                ProveedorEntity proveedor = proveedorService.obtenerProveedorPorId(ID);
//                ClienteEntity cliente = getCliente(factura);
//                ProductoEntity producto = getProducto(ID, factura);
//
//                Element facturaxml = createFacturaElement(document, factura, proveedor, cliente, producto);
//                facturasxml.appendChild(facturaxml);
//            }
//
//            document.getDocumentElement().appendChild(facturasxml);
//
//            TransformerFactory transformerFactory = TransformerFactory.newInstance();
//            Transformer transformer = transformerFactory.newTransformer();
//            DOMSource source = new DOMSource(document);
//
//            response.setContentType("application/xml");
//            response.setHeader("Content-Disposition", "attachment; filename=facturas.xml");
//
//            transformer.transform(source, new StreamResult(response.getOutputStream()));
//
//        } catch (ParserConfigurationException | TransformerException | IOException ex) {
//            System.out.println(ex.getMessage());
//        }
//    }
//
//    private FacturaEntity getFactura(String ID, String facturaID) {
//        List<FacturaEntity> facturaEntities = facturaService.ObtenerFacturas();
//        List<FacturaEntity> filteredFacturas = facturaEntities.stream()
//                .filter(factura -> factura.getProveedor().equals(ID))
//                .collect(Collectors.toList());
//        return filteredFacturas.stream()
//                .filter(f -> f.getFacturaId() == Integer.parseInt(facturaID))
//                .findFirst()
//                .orElse(null);
//    }
//
//    // Revisar
////    private ClienteEntity getCliente(FacturaEntity factura) {
////        List<ClienteEntity> clientesProveedor = clienteService.obtenerClientesPorProveedor(factura.getProveedor());
////        return clientesProveedor.stream()
////                .filter(c -> c.getClienteId().equals(factura.getCliente()))
////                .findFirst()
////                .orElse(null);
////    }
//
//    private ProductoEntity getProducto(String ID, FacturaEntity factura) {
//        List<ProductoEntity> productos = productoService.obtenerProductoPorProveedor(ID);
//        return productos.stream()
//                .filter(p -> p.getProductoId() == factura.getId_producto())
//                .findFirst()
//                .orElse(null);
//    }
//
//    private org.w3c.dom.Document createXmlDocument() throws ParserConfigurationException {
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//        DOMImplementation implementation = builder.getDOMImplementation();
//        return implementation.createDocument(null, "factura", null);
//    }
//
//    private Element createFacturaElement(org.w3c.dom.Document document, FacturaEntity factura, ProveedorEntity proveedor, ClienteEntity cliente, ProductoEntity producto) {
//        Element facturaxml = document.createElement("factura");
//
//        facturaxml.appendChild(createElementWithText(document, "facturaID", String.valueOf(factura.getFacturaId())));
//        facturaxml.appendChild(createProveedorElement(document, factura, proveedor));
//        facturaxml.appendChild(createClienteElement(document, factura, cliente));
//        facturaxml.appendChild(createProductoElement(document, factura, producto));
//        facturaxml.appendChild(createElementWithText(document, "cantidad", String.valueOf(factura.getCantidad())));
//        facturaxml.appendChild(createElementWithText(document, "total", String.valueOf(factura.getTotal())));
//        facturaxml.appendChild(createElementWithText(document, "fecha", String.valueOf(factura.getFecha())));
//
//        return facturaxml;
//    }
//
//    private Element createProveedorElement(org.w3c.dom.Document document, FacturaEntity factura, ProveedorEntity proveedor) {
//        Element proveedorxml = document.createElement("proveedor");
//
//        proveedorxml.appendChild(createElementWithText(document, "proveedorID", factura.getProveedor()));
//        proveedorxml.appendChild(createElementWithText(document, "proveedorNombre", proveedor.getNombre()));
//
//        return proveedorxml;
//    }
//
//    private Element createClienteElement(org.w3c.dom.Document document, FacturaEntity factura, ClienteEntity cliente) {
//        Element clientexml = document.createElement("cliente");
//
//        clientexml.appendChild(createElementWithText(document, "clienteID", factura.getCliente()));
//        clientexml.appendChild(createElementWithText(document, "nombreCliente", cliente.getNombre()));
//        clientexml.appendChild(createElementWithText(document, "correoCliente", cliente.getCorreoElectronico()));
//
//        return clientexml;
//    }
//
//    private Element createProductoElement(org.w3c.dom.Document document, FacturaEntity factura, ProductoEntity producto) {
//        Element productoxml = document.createElement("producto");
//
//        productoxml.appendChild(createElementWithText(document, "productoID", String.valueOf(factura.getId_producto())));
//        productoxml.appendChild(createElementWithText(document, "nombreProducto", producto.getNombre()));
//        productoxml.appendChild(createElementWithText(document, "precio", String.valueOf(producto.getPrecio())));
//
//        return productoxml;
//    }
//
//    private Element createElementWithText(org.w3c.dom.Document document, String elementName, String textContent) {
//        Element element = document.createElement(elementName);
//        Text text = document.createTextNode(textContent);
//        element.appendChild(text);
//        return element;
//    }

}
