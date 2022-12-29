package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.security.services.CommentaireService;
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
    final private CommentaireService commentaireService;

    @ApiOperation(value = "Ajout de commentaire")
    @PostMapping("/ajouter_commentaire")
    public Commentaire add(@RequestBody Commentaire commentaire){
        return commentaireService.add(commentaire);
    }

    @ApiOperation(value = "Modification des commentaires par id")
    @PutMapping ("/modifier_commentaire/{id_commentaire}")
    public Commentaire update(@PathVariable Long id_commentaire, @RequestBody Commentaire commentaire){
        return commentaireService.update(id_commentaire,commentaire);
    }

    @ApiOperation(value = "Suppression des commentaires par id")
    @DeleteMapping("/suprimer_commentaire/{id_commentaire}")
    public String delete(@PathVariable Long id_commentaire){
        return this.commentaireService.delete(id_commentaire);
    }

    @ApiOperation(value = "Affichage des commentaires par idée")
    @GetMapping("/afficherCommentaireParIdee/{id_idee}")
    public List<Commentaire> AfficherCommentaireParIdee(@PathVariable long id_idee) {
        return commentaireService.AfficherCommentaireParIdee(id_idee);
    }

}


