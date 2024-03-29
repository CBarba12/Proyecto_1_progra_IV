package com.tcna.primeraweb.progra_4.data;


import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProveedorRepository extends JpaRepository<ProveedorEntity,String> {
}
