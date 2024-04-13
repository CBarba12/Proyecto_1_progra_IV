package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;

import java.util.ArrayList;
import java.util.List;

public class HaciendaServiceStubProveedor {
    private List<ClienteEntity> clientesRegistrados;

    public HaciendaServiceStubProveedor() {
        this.clientesRegistrados = new ArrayList<>();
    }

    public boolean validarCliente(ClienteEntity cliente) {
        return clientesRegistrados.stream().anyMatch(u -> u.getCliente_id().equals(cliente.getCliente_id()));
    }
}
