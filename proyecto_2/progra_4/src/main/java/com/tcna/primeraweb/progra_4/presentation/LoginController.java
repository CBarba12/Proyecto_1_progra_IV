package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.Security.UserDetailsImp;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import com.tcna.primeraweb.progra_4.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.core.Authentication;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/LoginController")
public class LoginController {

    @Autowired
    private ProveedorService proveedorService;

    @Autowired
    private ClienteService clienteService;



    @PostMapping("/login")
    public ProveedorEntity login(@RequestBody ProveedorEntity form,  HttpServletRequest request) {
        try {
            request.login(form.getIdProveedor(), form.getContrasena());
        } catch (ServletException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        Authentication auth = (Authentication) request.getUserPrincipal();
        ProveedorEntity user = ((UserDetailsImp) auth.getPrincipal()).getUser();
        return new ProveedorEntity(user.getIdProveedor(), user.getNombre(), user.getCorreoElectronico(),
                null, user.getEstado(), user.getAdmin(), user.getTelefono(), user.getDireccion(),
                user.getTipoProveedor(), user.getActividadComercial());
    }


    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        try {
            request.logout();
        } catch (ServletException e) {
        }
    }



    @GetMapping("/inicio")
    public String inicio( ) {
        return "login";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/current-user")
    public ProveedorEntity getCurrentUser(@AuthenticationPrincipal UserDetailsImp user) {
        return new ProveedorEntity(user.getUser().getIdProveedor(), user.getUser().getNombre(), user.getUser().getCorreoElectronico(),
                null, user.getUser().getEstado(), user.getUser().getAdmin(), user.getUser().getTelefono(), user.getUser().getDireccion(),
                user.getUser().getTipoProveedor(), user.getUser().getActividadComercial());
    }

}


