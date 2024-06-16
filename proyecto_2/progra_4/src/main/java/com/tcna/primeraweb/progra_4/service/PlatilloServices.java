package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.CategoriaRepository;
import com.tcna.primeraweb.progra_4.data.PlatilloRepository;
import com.tcna.primeraweb.progra_4.logic.Platillo;

import java.util.ArrayList;
import java.util.List;

public class PlatilloServices {

    CategoriaRepository categoriaRepository=new CategoriaRepository();
    PlatilloRepository platilloRepository=new PlatilloRepository(categoriaRepository);


    public PlatilloServices() {
    }

    public List<Platillo> findByCategoria(String id)
    {
        return  platilloRepository.findByCategoria(id);
    }

    public Platillo findById(String id)
    {
        return platilloRepository.findById(id);
    }

    public List<Platillo> getAll()
    {
        return platilloRepository.getAll();
    }






}
