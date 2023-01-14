package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Jaime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JaimeRepository extends JpaRepository<Jaime, Long> {
}
