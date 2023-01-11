package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.*;
import com.sy.backEndApiAkilina.repository.BadwordRepository;
import com.sy.backEndApiAkilina.repository.CommentaireRepository;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.security.services.WordFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class WordFilterServiceImpl implements WordFilterService {

    @Autowired
    private BadwordRepository badwordRepository;

    @Autowired
    private IdeeRepository ideeRepository;

    private List<String> badWords;
    @Autowired
    private CommentaireRepository commentaireRepository;

    @PostConstruct
    public void init() {
        List<BadWord> badWordsEntities = badwordRepository.findAll();
        badWords = badWordsEntities.stream().map(BadWord::getWord).collect(Collectors.toList());
    }

    @Override
    public String filterIdee(Idee idee) {
        for (String word : badWords) {
            if(idee.getContenu_idee().toLowerCase().contains(word.toLowerCase()))
                return "S'il vous plaît utilisez des mots appropriés";
        }
        idee.setDate(new Date());


        ideeRepository.save(idee);
        return "idee ajouter avec succès";
    }

    @Override
    public String filterCommentaire(String content) {
        for (String word : badWords) {
            if(content.toLowerCase().contains(word.toLowerCase()))
                return "S'il vous plaît utilisez des mots appropriés";
        }
        Commentaire commentaire = new Commentaire();
        commentaire.setContenu_commentaire(content);

        commentaireRepository.save(commentaire);
        return "commentaire ajouter avec succès";
    }
}
