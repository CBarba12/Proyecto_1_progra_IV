package com.tcna.primeraweb.progra_4.Security;

import com.tcna.primeraweb.progra_4.data.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    ProveedorRepository proveedorRepository;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        try {
            return new UserDetailsImp(proveedorRepository.findById(id).orElse(null));
        } catch (Exception e) {
            throw new UsernameNotFoundException("Username " + id + " not found");
        }
    }
}
