package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.logic.Platillo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/platillos")

public class PlatilloController {

    private PlatilloController platilloServices=new PlatilloController();



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

    @GetMapping("/listar_porcategoria")
    public List<Platillo> nombre_Categoria(String nom){

        List<Platillo> platilloCategoria = new ArrayList<>();

        for (Platillo platillo : platilloServices.getAll()) {

            if (platillo.getCategoria().getId().equals(nom)) {
                platilloCategoria.add(platillo);
            }
        }

        return platilloCategoria;
    }










}
