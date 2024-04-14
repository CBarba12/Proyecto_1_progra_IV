package com.tcna.primeraweb.progra_4.data;

import com.tcna.primeraweb.progra_4.logic.ClienteEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface ClienteRepository extends JpaRepository<ClienteEntity,String> {


    List<ClienteEntity> findByProveedorId(String proveedorId);

}
