package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.CategoriaRepository;
import com.tcna.primeraweb.progra_4.data.PlatilloRepository;
import com.tcna.primeraweb.progra_4.logic.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaServices {


    CategoriaRepository categoriaRepository=new CategoriaRepository();




    public Categoria findById(String id){
        return categoriaRepository.findById(id);
    }

    public  List<String> optener_nombre (){
        List<String> nombres = new ArrayList<>();

        List<Categoria> categorias = categoriaRepository.getAll();
        for (Categoria categoria : categorias) {
            nombres.add(categoria.getNombre());
        }




        return nombres;
    }



    public List<Categoria> getAll(){
        return categoriaRepository.getAll();
    }




}
