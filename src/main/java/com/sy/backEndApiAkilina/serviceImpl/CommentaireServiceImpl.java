package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.CommentaireRepository;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.security.services.CommentaireService;
import com.sy.backEndApiAkilina.security.services.WordFilterService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//Annotation permettant de gérer les problèmes de constructeur pour tous les champs
@AllArgsConstructor
@Service

public class CommentaireServiceImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;

    private final WordFilterService wordFilterService;
    private final IdeeRepository ideeRepository;

    @Override
    public String add(Commentaire commentaire, User user, Idee idee) {
        Boolean filteredContent = wordFilterService.filterCommentaire(commentaire);
        if (filteredContent)
            return "Veuillez utiliser des mots approprié";
        else {
            commentaire.setUser(user);
            commentaire.setIdee(idee);
            commentaireRepository.save(commentaire);
            return "Commentaire ajouté avec succès";
        }
    }

    @Override
    public List<Commentaire> read() {
        return commentaireRepository.findAll();
    }

    @Override
    public String update(Long id_commentaire, Commentaire commentaire) {
        if(!wordFilterService.filterCommentaire(commentaire)){
            return commentaireRepository.findById(id_commentaire)
                            .map(commentaire1 -> {
                            commentaire1.setContenu_commentaire(commentaire.getContenu_commentaire());
                                commentaireRepository.save(commentaire1);
                                return "Commentaire modifier avec succes";
                            }).orElseThrow();
        }else {
            return "Veuillez utiliser des mots appropriés";
        }
    }

    @Override
    public String delete(Long id_commentaire) {
        commentaireRepository.deleteById(id_commentaire);
        return "Commentaire supprimé avec succès";
    }

    @Override
    public List<Commentaire> AfficherCommentaireParIdIdee(Idee idee) {
        return commentaireRepository.findByIdee(idee);
    }

}

