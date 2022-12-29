package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Ministere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinistereRepository extends JpaRepository <Ministere, Long>{

Ministere findByLibelle(String libelle);
}
