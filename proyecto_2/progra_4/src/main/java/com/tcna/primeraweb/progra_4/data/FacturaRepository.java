package com.tcna.primeraweb.progra_4.data;

import com.tcna.primeraweb.progra_4.logic.FacturaEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface FacturaRepository extends JpaRepository<FacturaEntity,Integer> {


}



