package com.tcna.primeraweb.progra_4.data;


import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProveedorRepository extends JpaRepository<ProveedorEntity,String> {


    ProveedorEntity findByIdProveedorAndContrasena(String ID, String contrasena);
    List<ProveedorEntity> findByActivoTrue();
    List<ProveedorEntity> findByActivoFalse();

    ProveedorEntity findByIdProveedorAndActivo(String idProveedor, boolean activo);


}
