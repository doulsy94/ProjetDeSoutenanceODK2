package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.security.services.IdeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

//Annotation permettant de gérer les problèmes de constructeur pour tous les champs
@AllArgsConstructor
@Service
public class IdeeServiceImpl implements IdeeService {
    private final IdeeRepository ideeRepository;
    private final MinistereRepository ministereRepository;

    @Override
    public Idee add(Idee idee) {
        return ideeRepository.save(idee);
    }

    @Override
    public List<Idee> read() {
        return ideeRepository.findAll();
    }

    @Override
    public Idee update(Long id_idee, Idee idee) {
        return ideeRepository.findById(id_idee)
                .map(idee1 -> {
                    idee1.setContenu_idee(idee1.getContenu_idee());
                    return ideeRepository.save(idee1);
                }).orElseThrow(() -> new RuntimeException("Idee non trouvé !"));
    }

    @Override
    public String delete(Long id_idee) {
        ideeRepository.deleteById(id_idee);
        return "Idée supprimé avec succès";
    }

    @Override
    public List<Idee> AfficherIdeeParMinistere(Long id_ministere) {

        Ministere ministere = (Ministere) ministereRepository.findById(id_ministere).get();
        List<Idee>  idees = new ArrayList<>();
        List<Idee> toutidee= ideeRepository.findAll();

        for(Idee ide : toutidee) {
            try {
                if (ide.getId_ministere().equals(ministere.getId_ministere())) {
                    idees.add(ide);
                }
            } catch (Exception e) {
                System.out.println("erreur survenue lors de l'affichage des idees par ministere");
            }
        }
        return idees;
    }

  /*  @Override
    public List<Object[]> readIdeeOfMinistere(String ministere) {
        return ideeRepository.FINDIDEENFONCTIONMINISTERE(ministere);
    }*/


}

