package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Vocal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VocalRepository extends JpaRepository <Vocal, Long>{

}
