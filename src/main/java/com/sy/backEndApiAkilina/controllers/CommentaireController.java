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
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
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
    public String add(@RequestBody Commentaire commentaire, @PathVariable("id_user") Long id_user, @PathVariable("id_idee") Long id_idee) {
       try {
        User user= userRepository.findById(id_user).get();
        Idee idee = ideeRepository.findById(id_idee).get();
        commentaire.setIdee(idee);
        commentaire.setUser(user);

        return commentaireService.add(commentaire, user, idee);
    }catch (Exception e){
           return e.getMessage();
       }
    }

    @ApiOperation(value = "Lire toutes les commentaires")
    @GetMapping("/lire")
    public List<Commentaire> read() {
        return commentaireService.read();
    }

    @ApiOperation(value = "Nombre de Commentaire")
    @GetMapping("/afficher_commentaire_nombre")
    public int readNombre() {return commentaireService.read().size();
    }

    @ApiOperation(value = "Modification des commentaires par id")
    @PutMapping ("/modifier/{id_commentaire}/{id_user}")
    public String update(@PathVariable Long id_commentaire, @PathVariable Long id_user, @RequestBody Commentaire commentaire){
       try {
           Commentaire commentaire1 = commentaireRepository.findById(id_commentaire).get();
           if (commentaire1.getUser().getId_user() == id_user) {
               return commentaireService.update(id_commentaire, commentaire);
           }else {
               return "vous n'etes pas autorisé à faire cette action";
           }
       }catch (Exception e){
           return e.getMessage();
       }

    }

    @ApiOperation(value = "Suppression des commentaires par id")
    @DeleteMapping("/suprimer/{id_commentaire}/{id_user}")
    public String delete(@PathVariable Long id_commentaire, @PathVariable Long id_user) {
        try {
            Commentaire commentaire = commentaireRepository.findById(id_commentaire).get();
            if (commentaire.getUser().getId_user() == id_user) {
                return commentaireService.delete(id_commentaire);
            } else {
                return "vous n'etes pas autorisé à faire cette action";
            }
        }catch (Exception e){
           return e.getMessage();
        }
    }

    @ApiOperation(value = "Affichage des commentaires par Id idee")
    @GetMapping("/afficherCommentaireParIdIdee/{id_idee}")
    public Object AfficherCommentaireParIdIdee(@PathVariable long id_idee) {
        try {
            Idee idee = ideeRepository.findById(id_idee).get();
            System.out.println(idee);
            return commentaireService.AfficherCommentaireParIdIdee(idee);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

}


