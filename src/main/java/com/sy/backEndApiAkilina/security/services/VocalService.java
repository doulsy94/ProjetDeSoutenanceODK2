package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Vocal;

import java.util.List;

public interface VocalService {

    //methode permettant d'ajouter un vocal
  //  Vocal add(Vocal vocal);

    void add(byte[] vocal_contenu);

    List<Vocal> read();

    List<Vocal> AfficherVocalParMinistere(Long id_ministere);

    //methode permettant de de supprimer un vocal
    String delete(Long id_vocal);
}
