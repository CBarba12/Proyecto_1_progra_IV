package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.logic.Categoria;
import com.tcna.primeraweb.progra_4.logic.Platillo;
import com.tcna.primeraweb.progra_4.service.CategoriaServices;
import com.tcna.primeraweb.progra_4.service.PlatilloServices;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/platillos")

public class PlatilloController {

    private PlatilloServices platilloServices=new PlatilloServices();
    private CategoriaServices categoriaServices=new CategoriaServices();


    public PlatilloController() {
    }

    @GetMapping("/Encontrar/{ID}")
    public Platillo findById(@PathVariable("ID")String id){
        return platilloServices.findById(id);
    }

    @GetMapping("/listar")
    public List<Platillo> getAll(){
        return platilloServices.getAll();
    }

    @GetMapping("/listarplatillo/{nom}")
    public List<String> nombre_Categoria(@PathVariable("nom") String nom){

        List<String> platilloCategoria = new ArrayList<>();

        for (Platillo platillo : platilloServices.getAll()) {

            if (platillo.getCategoria().getNombre().equals(nom)) {
                platilloCategoria.add(platillo.getNombre() +" "+platillo.getDescripcion() + " "+platillo.getPrecio());
            }
        }

        return platilloCategoria;
    }



    @GetMapping("/listar_por_categoria")
    public List<String> categorias(){

        List<String> categorias = new ArrayList<>();

        for (Categoria platillo : categoriaServices.getAll()) {


                categorias.add(platillo.getNombre());

        }

        return categorias;
    }








}
