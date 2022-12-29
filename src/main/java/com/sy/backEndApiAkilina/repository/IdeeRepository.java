package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Idee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IdeeRepository extends JpaRepository <Idee, Long>{
    //Trouvé les idée par rapport à un ministere donnée
   /* @Query(value = "select idee.contenu_idee from idee, ministere where idee.idministere_id = ministere.id_ministere " +
            "and  ministere.libelle =:ministere", nativeQuery = true )
    public List<Object[]> FINDIDEENFONCTIONMINISTERE(@Param("ministere") String ministere);//@param fait reference parametre à afficher

    Idee findById(String id_idee);*/


}
