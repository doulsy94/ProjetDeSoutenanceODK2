package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.models.Ministere;

import java.util.List;

public interface CommentaireService {

    //methode permettant d'ajouter un commentaire en fonction d'un idée
    Commentaire add(Commentaire commentaire);

    //methode permettant de lire commentaire
    List<Commentaire> read();

    //methode permettant de modifier un commentaire
    Commentaire update(Long id_commentaire, Commentaire commentaire);
    //methode permettant de de supprimer un commentaire
    String delete1(Long id_commentaire);

    List<Commentaire> AfficherCommentaireParIdee(Long id_idee);

}
