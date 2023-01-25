package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.*;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.repository.RoleRepository;
import com.sy.backEndApiAkilina.security.services.IdeeService;
import com.sy.backEndApiAkilina.security.services.WordFilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

//Annotation permettant de gérer les problèmes de constructeur pour tous les champs
@AllArgsConstructor
@Service
public class IdeeServiceImpl implements IdeeService {
    private final IdeeRepository ideeRepository;

    private final WordFilterService wordFilterService;

    private RoleRepository roleRepository;

    private final MinistereRepository ministereRepository;

    @Override
    public String add(Idee idee, User user, Ministere ministere) {
        Boolean filteredContent = wordFilterService.filterIdee(idee);
        if (filteredContent)
            return "Veuillez utiliser des mots approprié";
        else {
            Date date= new Date();
            Notification notification = new Notification();
            notification.setCreateur(user.getUsername());
            notification.setMinistere(ministere.getLibelle());
            notification.setDatenotif(date);
            notification.setIdee(idee);
            idee.setNotification(notification);
            idee.setDate(date);
            idee.setUser(user);
            idee.setMinistere(ministere);
            ideeRepository.save(idee);
            return "Idee ajouté avec succès";
        }
    }

    @Override
    public List<Idee> read() {
        return ideeRepository.findAll();
    }

    @Override
    public String update(Long id_idee, Idee idee) {
        if (!wordFilterService.filterIdee(idee)) {
            return ideeRepository.findById(id_idee)
                    .map(idee1 -> {
                        idee1.setContenu_idee(idee.getContenu_idee());
                        ideeRepository.save(idee1);
                        return "Idee modifier avec succes";
                    }).orElseThrow();
        } else {
            return "Veuillez utiliser des mots appropriés";
        }

    }


    @Override
    public String delete(Long id_idee) {
        ideeRepository.deleteById(id_idee);
        return "Idée supprimé avec succès";
    }

    @Override
    public List<Idee> AfficherIdeeParLibelleMinistere(String libelle) {

        List<Idee> idees =ideeRepository.findAll();
        List<Idee> idees1 = new ArrayList<>();
        for(Idee ide:idees){
            if(ide.getMinistere().getLibelle().equals(libelle)){
                idees1.add(ide);
            }
        }

        return  idees1;
    }

    @Override
    public List<Idee> AfficherIdeeParIdMinistere(Ministere ministere) {
        return ideeRepository.findByMinistere(ministere);
    }

    @Override
    public Optional<Idee> trouverIdeeParID(long id_idee) {
        return ideeRepository.findById(id_idee);
    }


}
