package com.tcna.primeraweb.progra_4.data;

import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import com.tcna.primeraweb.progra_4.logic.ProveedorEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ProductoRepository extends JpaRepository<ProductoEntity,Integer> {

    List<ProductoEntity> findByProveedorId(String nameproducto);


}
