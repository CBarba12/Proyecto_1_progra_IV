package com.tcna.primeraweb.progra_4.Security;

import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImp implements UserDetails {

    private ProveedorEntity user;

    public UserDetailsImp(ProveedorEntity user) {
        this.user = user;
    }

    public ProveedorEntity getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> autoridades = new ArrayList<>();
        if (user.getAdmin() != 0) { // Si admin es diferente de 0, el usuario es administrador
            autoridades.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            autoridades.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return autoridades;


//
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        authorities.add(new SimpleGrantedAuthority(user.getAdmin()));
//        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getContrasena();
    }

    @Override
    public String getUsername() {
        return user.getIdProveedor();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}