package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.BadWord;
import com.sy.backEndApiAkilina.models.Jaime;

import java.util.List;

public interface JaimeService {

    Jaime add(Jaime jaime);
    List<Jaime> read();
    String delete(Long id);

}
