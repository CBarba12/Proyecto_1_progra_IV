package com.tcna.primeraweb.progra_4.presentation;

import com.tcna.primeraweb.progra_4.logic.Categoria;
import com.tcna.primeraweb.progra_4.service.CategoriaServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/platillos")
public class ControllerCategoria {

    private CategoriaServices categoriaServices=new CategoriaServices();


    public ControllerCategoria() {
    }


    @GetMapping("/Encontrar/{ID}")
    public Categoria findById(@PathVariable("ID")String id){
        return categoriaServices.findById(id);
    }


    @GetMapping("/listar")
    public List<Categoria> getAll(){
        return categoriaServices.getAll();
    }

    
}
