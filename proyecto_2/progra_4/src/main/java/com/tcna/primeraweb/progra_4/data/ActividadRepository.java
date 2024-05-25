package com.tcna.primeraweb.progra_4.data;

import com.tcna.primeraweb.progra_4.logic.ActividadEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public interface ActividadRepository extends JpaRepository<ActividadEntity,Integer> {



}
