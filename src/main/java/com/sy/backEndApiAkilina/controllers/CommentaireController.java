package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.CommentaireRepository;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.CommentaireService;
import com.sy.backEndApiAkilina.security.services.WordFilterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Api(value = "commentaire", description = "MANIPULATION DES DONNEES DE LA TABLE COMMENTAIRE")
@RestController
@RequestMapping("/api/commentaire")
@AllArgsConstructor
public class CommentaireController {

    @Autowired
     private final CommentaireService commentaireService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IdeeRepository ideeRepository;
    private CommentaireRepository commentaireRepository;

    private final WordFilterService wordFilterService;

    @ApiOperation(value = "Ajout de commentaire")
    @PostMapping("/ajouter/{id_user}/{id_idee}")
    public String add(@RequestBody Commentaire commentaire, @PathVariable("id_user") User user, @PathVariable("id_idee") Idee idee) {

        commentaire.setIdee(idee);
        commentaire.setUser(user);

        return commentaireService.add(commentaire, user, idee);
    }

    @ApiOperation(value = "Lire toutes les commentaires")
    @GetMapping("/lire")
    public List<Commentaire> read() {
        return commentaireService.read();
    }

    @ApiOperation(value = "Modification des commentaires par id")
    @PutMapping ("/modifier/{id_commentaire}")
    public String update(@PathVariable Long id_commentaire, @RequestBody Commentaire commentaire){
        return commentaireService.update(id_commentaire,commentaire);
    }

    @ApiOperation(value = "Suppression des commentaires par id")
    @DeleteMapping("/suprimer/{id_commentaire}")
    public String delete(@PathVariable Long id_commentaire){
        return this.commentaireService.delete(id_commentaire);
    }

    @ApiOperation(value = "Affichage des commentaires par Id idee")
    @GetMapping("/afficherCommentaireParIdIdee/{id_idee}")
    public List<Commentaire> AfficherCommentaireParIdIdee(@PathVariable long id_idee) {
        Idee idee = ideeRepository.findById(id_idee).get();
        System.out.println(idee);
        return commentaireService.AfficherCommentaireParIdIdee(idee);
    }

}


