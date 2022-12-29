package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.security.services.CommentaireService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/commentaire")
@AllArgsConstructor
public class CommentaireController {

    @Autowired
    final private CommentaireService commentaireService;

    @PostMapping("/ajouter_commentaire")
    public Commentaire add(@RequestBody Commentaire commentaire){
        return commentaireService.add(commentaire);
    }

    @PostMapping("/modifier_commentaire/{id_commentaire}")
    public Commentaire update(@PathVariable Long id_commentaire, @RequestBody Commentaire commentaire){
        return commentaireService.update(id_commentaire,commentaire);
    }

    @DeleteMapping("/suprimer_commentaire/{id_commentaire}")
    public String delete(@PathVariable Long id_commentaire){
        return this.commentaireService.delete1(id_commentaire);
    }
}


