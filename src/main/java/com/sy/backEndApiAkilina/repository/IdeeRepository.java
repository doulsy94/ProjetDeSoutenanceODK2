package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Ministere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IdeeRepository extends JpaRepository <Idee, Long>{
    public List<Idee> findByMinistere(Ministere ministere);
}
