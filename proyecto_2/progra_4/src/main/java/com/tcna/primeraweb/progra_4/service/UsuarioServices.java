package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.UsuarioRepository;
import com.tcna.primeraweb.progra_4.logic.Usuario;

@org.springframework.stereotype.Service
public class UsuarioServices {

    private UsuarioRepository usuarioRepository=new UsuarioRepository();





    public boolean login(String email, String password) {
        return usuarioRepository.verificar(email, password);
    }

    public boolean existe(String email) {
        return usuarioRepository.existe(email);
    }

    public void add(Usuario u) {
        usuarioRepository.add(u);
    }

    public void update(Usuario u) {
        usuarioRepository.update(u);
    }






}
