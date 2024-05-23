package com.tcna.primeraweb.progra_4.presentation;


import com.tcna.primeraweb.progra_4.logic.JwtRequest;
import com.tcna.primeraweb.progra_4.logic.JwtResponse;
import com.tcna.primeraweb.progra_4.service.Implements.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcna.primeraweb.progra_4.Configuration.JwtUtils;

@RestController
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            autenticar(jwtRequest.getUsername(), jwtRequest.getPassword());
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    public void autenticar(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        }catch (DisabledException e){
            throw new Exception("Usuario deshabilitado " + e.getMessage());
        }catch (BadCredentialsException e){
            throw new BadCredentialsException("Credenciales incorrectas" + e.getMessage());
        }


    }
}
