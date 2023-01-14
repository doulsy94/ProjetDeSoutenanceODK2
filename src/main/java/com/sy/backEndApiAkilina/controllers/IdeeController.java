package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.CommentaireRepository;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.CommentaireService;
import com.sy.backEndApiAkilina.security.services.IdeeService;
import com.sy.backEndApiAkilina.security.services.WordFilterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Api(value = "idee", description = "MANIPULATION DES DONNEES DE LA TABLE IDEE")
@RestController
@RequestMapping("/api/idee")
@AllArgsConstructor
//@ToString
public class IdeeController {


     private final IdeeService ideeService;

     private final CommentaireService commentaireService;

    private final MinistereRepository ministereRepository;
    private final IdeeRepository ideeRepository;
    private final CommentaireRepository commentaireRepository;
    private final UserRepository userRepository;

    private final WordFilterService wordFilterService;

    @ApiOperation(value = "Ajout d'idée")
    @PostMapping("/ajouter/{id_user}/{id_ministere}")
    public String add(@RequestBody Idee idee,@PathVariable("id_user") User user, @PathVariable("id_ministere") Ministere ministere) {

        idee.setUser(user);
        idee.setMinistere(ministere);

        return ideeService.add(idee, user, ministere);

    }

    // Liste = read = lecture
    @ApiOperation(value = "Lire idée de l'utilisateur")
    @GetMapping("/lire")
    public List<Idee> read() {
        return ideeService.read();
    }

    @ApiOperation(value = "Nombre de IDEE")
    @GetMapping("/afficher_idee_nombre")
    public int readNombre() {return ideeService.read().size();
    }

    @ApiOperation(value = "Modifier idée de l'utilisateur")
    @PutMapping("/modifier/{id_idee}")
    public String update(@PathVariable Long id_idee, @RequestBody Idee idee){
        return ideeService.update(id_idee, idee);
    }

    @ApiOperation(value = "Supprimer idée de l'utilisateur")
    @DeleteMapping("/suprimer/{id_idee}")
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

    @ApiOperation(value = "Affichage des idées par libelle du ministere")
    @GetMapping("/afficherIdeeParLibelleMinistere/{libelle}")
        public List<Idee> AfficherIdeeParLibelleMinistere(@PathVariable String libelle) {
        return ideeService.AfficherIdeeParLibelleMinistere(libelle);
    }

    @ApiOperation(value = "Affichage des idées par Id ministere")
    @GetMapping("/afficherIdeeParIdMinistere/{id_ministere}")
    public List<Idee> AfficherIdeeParIdMinistere(@PathVariable long id_ministere) {
        Ministere ministere = ministereRepository.findById(id_ministere).get();
        System.out.println(ministere);
        return ideeService.AfficherIdeeParIdMinistere(ministere);
    }

}

