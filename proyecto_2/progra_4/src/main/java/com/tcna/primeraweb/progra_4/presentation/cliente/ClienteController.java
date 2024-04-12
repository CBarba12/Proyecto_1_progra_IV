package com.tcna.primeraweb.progra_4.presentation.cliente;
import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import com.tcna.primeraweb.progra_4.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/registrar")
    public String showForm(Model model) {
        ClienteEntity cliente = new ClienteEntity();
        model.addAttribute("cliente", cliente);
        return "registroCliente";
    }
    @PostMapping("/registrar")
    public String submitForm(@Valid ClienteEntity cliente, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registroCliente";
        }
        return "redirect:/success";
    }

}