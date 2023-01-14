package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.JaimePas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JaimePasRepository extends JpaRepository<JaimePas, Long> {
}
