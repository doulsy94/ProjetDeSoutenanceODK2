package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.repository.VocalRepository;
import com.sy.backEndApiAkilina.security.services.VocalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//Annotation permettant de gérer les problèmes de constructeur pour tous les champs
@AllArgsConstructor
@Service
public class VocalServiceImpl implements VocalService {
    private final VocalRepository vocalRepository;

    @Override
    public String delete(Long id_vocal) {
        vocalRepository.deleteById(id_vocal);
        return "Vocal supprimé avec succès";
    }

}
