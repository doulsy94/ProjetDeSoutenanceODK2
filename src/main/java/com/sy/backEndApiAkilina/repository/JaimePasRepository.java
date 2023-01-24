package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Jaime;
import com.sy.backEndApiAkilina.models.JaimePas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JaimePasRepository extends JpaRepository<JaimePas, Long> {

    public List<JaimePas> findByIdee(Idee idee);

}
