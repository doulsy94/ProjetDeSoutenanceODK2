package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.*;

import java.util.List;

public interface JaimeService {

    Jaime add(Jaime jaime, User user, Idee idee);
    List<Jaime> read();
    String delete(Long id);

    List<Jaime> AfficherJaimeParIdIdee(Idee idee);

}
