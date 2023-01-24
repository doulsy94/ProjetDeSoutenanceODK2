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
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Api(value = "idee", description = "MANIPULATION DES DONNEES DE LA TABLE IDEE")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
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
    public String add(@RequestBody Idee idee,@PathVariable("id_user") Long id_user, @PathVariable("id_ministere") Long id_ministere) {
    try {
        User user= userRepository.findById(id_user).get();
        Ministere ministere = ministereRepository.findById(id_ministere).get();
        idee.setUser(user);
        idee.setMinistere(ministere);

     return ideeService.add(idee, user, ministere);

    }catch (Exception e){
      return e.getMessage();
}

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
    @PutMapping("/modifier/{id_idee}/{id_user}")
    public String update(@PathVariable Long id_idee, @PathVariable Long id_user, @RequestBody Idee idee){
        try {
            Idee idee1 = ideeRepository.findById(id_idee).get();

            if (idee1.getUser().getId_user() == id_user) {
                return ideeService.update(id_idee, idee);
            }else {
                return "vous n'etes pas autorisé à faire cette action";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @ApiOperation(value = "Supprimer idée de l'utilisateur")
    @DeleteMapping("/suprimer/{id_idee}/{id_user}")
    public String delete(@PathVariable Long id_idee, @PathVariable Long id_user) {
        try {
            Idee idee = ideeRepository.findById(id_idee).get();

            if (idee.getUser().getId_user() == id_user) {
                return ideeService.delete(id_idee);
            } else {
                return "vous n'etes pas autorisé à faire cette action";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "Affichage des idées par libelle du ministere")
    @GetMapping("/afficherIdeeParLibelleMinistere/{libelle}")
        public Object AfficherIdeeParLibelleMinistere(@PathVariable String libelle) {
        try {
            return ideeService.AfficherIdeeParLibelleMinistere(libelle);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @ApiOperation(value = "Affichage des idées par Id ministere")
    @GetMapping("/afficherIdeeParIdMinistere/{id_ministere}")
    public Object AfficherIdeeParIdMinistere(@PathVariable long id_ministere) {
        try {
            Ministere ministere = ministereRepository.findById(id_ministere).get();
            System.out.println(ministere);
            return ideeService.AfficherIdeeParIdMinistere(ministere);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @ApiOperation(value = "LIRE IDEE Par ID")
    @GetMapping("/lireParId/{id_idee}")
    public Object readIdeeParID(@PathVariable("id_idee") Long id_idee) {
        try {
            return ideeRepository.findById(id_idee);
        }catch (Exception e){
            return e.getMessage();
        }

    }

}

