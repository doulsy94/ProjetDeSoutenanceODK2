package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Jaime;
import com.sy.backEndApiAkilina.models.JaimePas;
import com.sy.backEndApiAkilina.models.User;

import java.util.List;

public interface JaimePasService {

    JaimePas add(JaimePas jaimePas, User user, Idee idee);
    List<JaimePas> read();
    String delete(Long id);

    List<JaimePas> AfficherJaimePasParIdIdee(Idee idee);

}
