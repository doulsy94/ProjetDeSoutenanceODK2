package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Idee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdeeRepository extends JpaRepository <Idee, Long>{


}
