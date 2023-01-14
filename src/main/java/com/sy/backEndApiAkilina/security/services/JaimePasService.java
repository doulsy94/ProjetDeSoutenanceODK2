package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Jaime;
import com.sy.backEndApiAkilina.models.JaimePas;

import java.util.List;

public interface JaimePasService {

    JaimePas add(JaimePas jaimePas);
    List<JaimePas> read();
    String delete(Long id);

}
