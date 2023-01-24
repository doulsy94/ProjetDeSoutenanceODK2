package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.FichierVocal;
import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.models.User;

import java.util.List;

public interface FichierVocalService {

    String add(FichierVocal fichierVocal, User user, Ministere ministere);

    String delete(Long id);

    //methode permettant d'afficher les vacales par id
   // FichierVocal trouverFichierVocalParId(Long id);

    List<FichierVocal> read();

    //methode permettant d'afficher les vocales par libelle de ministere
    List<FichierVocal> AfficherVocalParLibelleMinistere(String libelle);

    //methode permettant d'afficher les vocales par id de ministere
    List<FichierVocal> AfficherVocalParIdMinistere(Ministere ministere);
}
