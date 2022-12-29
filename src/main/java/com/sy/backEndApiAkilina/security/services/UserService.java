package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.models.User;

import java.util.List;

public interface UserService {

    //methode permettant de lire utilisateur
    List<User> read();

    //methode permettant de modifier un utilisateur
    User update(Long id_user, User user);

    //methode permettant de de supprimer un utilisateur
    String delete(Long id_user);

}
