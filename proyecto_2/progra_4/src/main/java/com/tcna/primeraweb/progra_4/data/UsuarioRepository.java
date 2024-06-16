package com.tcna.primeraweb.progra_4.data;

import com.tcna.primeraweb.progra_4.logic.Usuario;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    List<Usuario> list;



    public UsuarioRepository() {
        list=new ArrayList<Usuario>();
        list.add(new Usuario("admin","1","admin","admin","hola"));
        list.add(new Usuario("user","2","user","user","hola"));
        list.add(new Usuario("user2","3","user2","user2","hola"));

    }

    public Usuario get(String id){
        for(Usuario u:list){
            if(u.getId().equals(id)){
                return u;
            }
        }
        return null;
    }

    public void add(Usuario u){
        list.add(u);
    }

    public void update(Usuario u){
        for(int i=0;i<list.size();i++){
            if(list.get(i).getNombre().equals(u.getNombre())){
                list.set(i, u);
            }
        }
    }


    public boolean existe(String nombre){
        for(Usuario u:list){
            if(u.getNombre().equals(nombre)){
                return true;
            }
        }
        return false;
    }

    public boolean verificar(String id, String password){
        for(Usuario u:list){
            if(u.getId().equals(id) && u.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }






}
