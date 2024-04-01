package com.tcna.primeraweb.progra_4.data;

import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import com.tcna.primeraweb.progra_4.logic.ProductoEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FacturaRepository extends JpaRepository<FacturaEntity,Integer> {

}



