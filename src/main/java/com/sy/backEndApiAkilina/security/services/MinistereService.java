package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Ministere;

import java.util.List;

public interface MinistereService {

    //methode permettant de creer un ministère
    Ministere add(Ministere ministere);

    //methode permettant de lire ministère
    List<Ministere> read();

    //methode permettant de modifier une region
    Ministere update(Long id_ministere, Ministere ministere);
    //methode permettant de de supprimer une region
    String delete(Long id_ministere);

    Ministere trouverMinistereParLibelle(String libelle);
}
