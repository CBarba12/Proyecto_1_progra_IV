package com.tcna.primeraweb.progra_4.service;

import com.tcna.primeraweb.progra_4.data.ActividadRepository;
import com.tcna.primeraweb.progra_4.logic.ActividadEntity;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ActividadService {
    @Getter
    @Autowired
    private ActividadRepository actividadRepository;

    public Boolean crear (ActividadEntity actividad){
        List<ActividadEntity> actividades = actividadRepository.findAll();
        for (ActividadEntity actividadExistente : actividades) {
            if (actividadExistente.getIdProveedor().equals(actividad.getIdProveedor()) && actividadExistente.getNombreActividad().equals(actividad.getNombreActividad())) {
                // Si ya existe una entrada con el mismo idProveedor y nombreActividad, no guardamos la nueva entidad
                return false;
            }
        }
        return actividadRepository.save(actividad) != null;
    }

    public Boolean eliminar (ActividadEntity actividad){
        List<ActividadEntity> actividadesProveedor = actividadRepository.findAll().stream()
                .filter(a -> a.getIdProveedor().equals(actividad.getIdProveedor()))
                .collect(Collectors.toList());

        if (actividadesProveedor.size() > 1) {
            actividadRepository.delete(actividad);
            return true;
        } else {
            return false;
        }
    }

    public List<ActividadEntity> actividadesPorProveedor(String proveedor) {
        return actividadRepository.findAll().stream()
                .filter(a -> a.getIdProveedor().equals(proveedor))
                .collect(Collectors.toList());
    }
}
