package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Jaime;
import com.sy.backEndApiAkilina.models.JaimePas;
import com.sy.backEndApiAkilina.models.User;
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
    public JaimePas add(JaimePas jaimePas, User user, Idee idee) {
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

    @Override
    public List<JaimePas> AfficherJaimePasParIdIdee(Idee idee) {
        return jaimePasRepository.findByIdee(idee);
    }

}
