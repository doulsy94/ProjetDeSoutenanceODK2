package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.repository.CommentaireRepository;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.security.services.CommentaireService;
import com.sy.backEndApiAkilina.security.services.IdeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Api(value = "idee", description = "MANIPULATION DES DONNEES DE LA TABLE IDEE")
@RestController
@RequestMapping("/api/idee")
@AllArgsConstructor
public class IdeeController {


    final private IdeeService ideeService;

    final private CommentaireService commentaireService;

    private final MinistereRepository ministereRepository;
    private final IdeeRepository ideeRepository;
    private final CommentaireRepository commentaireRepository;

    @ApiOperation(value = "Ajout d'idée")
    @PostMapping("/ajouter_idee")
    public Idee add(@RequestBody Idee idee){
        return ideeService.add(idee);
    }

    // Liste = read = lecture
    @ApiOperation(value = "Lire idée de l'utilisateur")
    @GetMapping("/lire_idee")
    public List<Idee> read() {
        return ideeService.read();
    }

    @ApiOperation(value = "Modifier idée de l'utilisateur")
    @PutMapping("/modifier_idee/{id_idee}")
    public Idee update(@PathVariable Long id_idee, @RequestBody Idee idee){
        return ideeService.update(id_idee, idee);
    }

    @ApiOperation(value = "Supprimer idée de l'utilisateur")
    @DeleteMapping("/suprimer_idee/{id_idee}")
    public String delete(@PathVariable Long id_idee){
        return this.ideeService.delete(id_idee);
    }

  /*  @ApiOperation(value = "LISTE DES IDEE D'UN MINISTERE DONNEE")
    @GetMapping("/lire_idee_ministere/{ministere}")
    public List<Object[]> FINDIDEENFONCTIONMINISTERE(@PathVariable String ministere) {
        List<Object[]> list = ideeService.readIdeeOfMinistere(ministere);

        return list;
    }*/
// Pour commentaire
  /*  @PostMapping("/ajouter_commentaire")
    public Commentaire add(@RequestBody Commentaire commentaire){
        return commentaireService.add(commentaire);
    }

    @PostMapping("/modifier_commentaire/{id_commentaire}")
    public Commentaire update(@PathVariable Long id_commentaire, @RequestBody Commentaire commentaire){
        return commentaireService.update(id_commentaire,commentaire);
    }

    @DeleteMapping("/suprimer_commentaire/{id_commentaire}")
    public String delete1(@PathVariable Long id_commentaire){
        return this.commentaireService.delete(id_commentaire);
    }

    //METHODE PERMETTANT DE VOIR LES COMMENTAIRE D'UNE IDEE
    @GetMapping("/lire_commentaire")
    public List<Commentaire> getComment(){
        return commentaireService.read();
    }
*/
   /* @GetMapping("/lireCommentairebyID/{id_idee}")
    public List<Commentaire> read(@PathVariable("id_idee") String id_idee){
        Idee idee = ideeRepository.findById(id_idee);
        return commentaireRepository.findByIdIdee(idee);
    }*/

    @ApiOperation(value = "Affichage des idées par ministere")
    @GetMapping("/afficherIdeeParMinistere/{id_ministere}")
        public List<Idee> AfficherIdeeParMinistere(@PathVariable long id_ministere) {
        return ideeService.AfficherIdeeParMinistere(id_ministere);
    }

}

