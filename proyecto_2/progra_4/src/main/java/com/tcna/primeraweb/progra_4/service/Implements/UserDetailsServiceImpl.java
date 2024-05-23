package com.tcna.primeraweb.progra_4.service.Implements;

import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ProveedorRepository proveedorRepository;



    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        ProveedorEntity proveedor = (ProveedorEntity) this.proveedorRepository.findAllById(Collections.singleton(id));
        if (proveedor == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return proveedor;
    }
}
