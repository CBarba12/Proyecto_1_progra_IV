package com.tcna.primeraweb.progra_4.data;

import com.tcna.primeraweb.progra_4.logic.Categoria;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component("categoriaRepository")
public class CategoriaRepository {
    List<Categoria> list;




    public CategoriaRepository() {
        list = new ArrayList<Categoria>();

        list.add(new Categoria("111","Entradas"));
        list.add(new Categoria("222","Carnes"));
        list.add(new Categoria("333","Sopas"));
        list.add(new Categoria("444","Arroces"));
        list.add(new Categoria("555","Bebidas"));
        list.add(new Categoria("666","Postres"));
    }


    public Categoria findById(String id){
        for(Categoria c:list){
            if(c.getId().equals(id)){
                return c;
            }
        }
        return null;
    }


   public List<Categoria> getAll(){
        return list;
    }


}
