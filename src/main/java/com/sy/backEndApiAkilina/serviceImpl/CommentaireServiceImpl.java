package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.repository.CommentaireRepository;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.security.services.CommentaireService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Annotation permettant de gérer les problèmes de constructeur pour tous les champs
@AllArgsConstructor
@Service

public class CommentaireServiceImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;
    private final IdeeRepository ideeRepository;

    @Override
    public Commentaire add(Commentaire commentaire) {
        return commentaireRepository.save(commentaire);
    }

    @Override
    public List<Commentaire> read() {
        return commentaireRepository.findAll();
    }

    @Override
    public Commentaire update(Long id_commentaire, Commentaire commentaire) {
        return commentaireRepository.findById(id_commentaire)
                .map(commentaire1 -> {
                    commentaire1.setContenu_commentaire(commentaire1.getContenu_commentaire());
                    return commentaireRepository.save(commentaire1);
                }).orElseThrow(() -> new RuntimeException("Commentaire non trouvé !"));
    }

    @Override
    public String delete(Long id_commentaire) {
        commentaireRepository.deleteById(id_commentaire);
        return "Commentaire supprimé avec succès";
    }

    @Override
    public List<Commentaire> AfficherCommentaireParIdee(Long id_idee) {

        Idee idee = (Idee) ideeRepository.findById(id_idee).get();
        List<Commentaire>  commentaires = new ArrayList<>();
        List<Commentaire> toutcommentaire = commentaireRepository.findAll();

        for(Commentaire com : toutcommentaire) {
            try {
                if (com.getContenu_commentaire().equals(idee.getId_idee())) {
                    commentaires.add(com);
                }
            } catch (Exception e) {
                System.out.println("erreur survenue lors de l'affichage des commentaires par idee");
            }
        }
        return commentaires;
    }
}

