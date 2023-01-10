package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.models.Vocal;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.repository.VocalRepository;
import com.sy.backEndApiAkilina.security.services.VocalService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Annotation permettant de gérer les problèmes de constructeur pour tous les champs
@AllArgsConstructor
@Service
public class VocalServiceImpl implements VocalService {
    private final VocalRepository vocalRepository;

    private final MinistereRepository ministereRepository;

   /* @Override
    public Vocal add(Vocal vocal) {
        return vocalRepository.save(vocal);
    }*/

    @Override
    public void add(byte[] vocal_contenu) {

    }

    @Override
    public List<Vocal> read() {
        return vocalRepository.findAll();
    }

    @Override
    public List<Vocal> AfficherVocalParMinistere(Long id_ministere) {
        Ministere ministere = (Ministere) ministereRepository.findById(id_ministere).get();
        List<Vocal>  vocale = new ArrayList<>();
        List<Vocal> toutvocal= vocalRepository.findAll();

        for(Vocal voc : toutvocal) {
            try {
                if (voc.getContenu_vocal().equals(ministere.getId_ministere())) {
                    vocale.add(voc);
                }
            } catch (Exception e) {
                System.out.println("erreur survenue lors de l'affichage des vocales par ministere");
            }
        }
        return vocale;
    }

    @Override
    public String delete(Long id_vocal) {
        vocalRepository.deleteById(id_vocal);
        return "Vocal supprimé avec succès";
    }

}
