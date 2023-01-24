package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Jaime;
import com.sy.backEndApiAkilina.models.Ministere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JaimeRepository extends JpaRepository<Jaime, Long> {

    public List<Jaime> findByIdee(Idee idee);


}
