package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.security.services.IdeeService;
import com.sy.backEndApiAkilina.security.services.WordFilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Annotation permettant de gérer les problèmes de constructeur pour tous les champs
@AllArgsConstructor
@Service
public class IdeeServiceImpl implements IdeeService {
    private final IdeeRepository ideeRepository;

    private final WordFilterService wordFilterService;

    private final MinistereRepository ministereRepository;

    @Override
    public String add(Idee idee, User user, Ministere ministere) {
        String filteredContent = wordFilterService.filterIdee(idee);
        if(!filteredContent.equals(idee.getContenu_idee()))
            return filteredContent;
        // return "S'il vous plaît utilisez des mots appropriés, les gros mots ne sont pas autorisés";
        idee.setContenu_idee(filteredContent);
        idee.setContexte(idee.getContexte());
        idee.setDate(new Date());
        idee.setId_user(user);
        idee.setId_ministere(ministere);
        ideeRepository.save(idee);
        return "Idee ajouté avec succès";
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
                    idee1.setContexte(idee1.getContexte());
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

