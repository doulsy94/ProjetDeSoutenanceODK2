package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Idee;
import java.util.List;

public interface IdeeService {

    //methode permettant d'ajouter une idéé en fonction d'un ministere
    String add(Idee idee);

    //methode permettant de lire les idees
    List<Idee> read();

    //methode permettant de modifier une idee
    Idee update(Long id_idee, Idee idee);
    //methode permettant de de supprimer une idee
    String delete(Long id_idee);

    List<Idee> AfficherIdeeParMinistere(Long id_ministere);


    //methode permettant de lister les idees d'un ministere
    //List<Object[]> readIdeeOfMinistere(String ministere);

}
