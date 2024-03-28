package com.tcna.primeraweb.project_progra.data;

import com.tcna.primeraweb.project_progra.logic.ProductoEntity;
import com.tcna.primeraweb.project_progra.logic.ProveedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<ProductoEntity,Integer> {
}
