package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.JaimePas;
import com.sy.backEndApiAkilina.repository.JaimePasRepository;
import com.sy.backEndApiAkilina.security.services.JaimePasService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class JaimePasServiceImpl implements JaimePasService {

    private final JaimePasRepository jaimePasRepository;

    @Override
    public JaimePas add(JaimePas jaimePas) {
        return jaimePasRepository.save(jaimePas);
    }

    @Override
    public List<JaimePas> read() {
        return jaimePasRepository.findAll();
    }
    @Override
    public String delete(Long id) {
        jaimePasRepository.deleteById(id);
        return "J'aime Pas supprimé avec succès";
    }

}
