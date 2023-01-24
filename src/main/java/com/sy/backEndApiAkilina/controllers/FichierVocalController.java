package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.FichierVocal;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.FichierVocalRepository;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.FichierVocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "vocal", description = "MANIPULATION DES DONNEES DE LA TABLE VOCAL")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/vocal")

@AllArgsConstructor
public class FichierVocalController {
    private final UserRepository userRepository;
    private final FichierVocalService fichierVocalService;

    private final MinistereRepository ministereRepository;
    private final FichierVocalRepository fichierVocalRepository;

    @ApiOperation(value = "Ajout de vocal")
    @PostMapping("/ajouter/{id_user}/{id_ministere}")
    public String add(@RequestBody FichierVocal fichierVocal, @PathVariable("id_user") Long id_user, @PathVariable("id_ministere") Long id_ministere) {
        try {
            User user= userRepository.findById(id_user).get();
            Ministere ministere = ministereRepository.findById(id_ministere).get();
            fichierVocal.setUser(user);
            fichierVocal.setMinistere(ministere);

            return fichierVocalService.add(fichierVocal, user, ministere);

        }catch (Exception e){
            return e.getMessage();
        }
    }

    @ApiOperation(value = "Lire les vocales de l'utilisateur")
    @GetMapping("/lire")
    public List<FichierVocal> read() {
        return fichierVocalService.read();
    }

    @ApiOperation(value = "Supprimer vocal de l'utilisateur")
    @DeleteMapping("/suprimer/{id_idee}/{id_user}")
    public String delete(@PathVariable Long id_idee, @PathVariable Long id_user) {
        try {
            FichierVocal fichierVocal = fichierVocalRepository.findById(id_idee).get();

            if (fichierVocal.getUser().getId_user() == id_user) {
                return fichierVocalService.delete(id_idee);
            } else {
                return "vous n'etes pas autorisé à faire cette action";
            }
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "Affichage des vocales par libelle du ministere")
    @GetMapping("/afficherVocalParLibelleMinistere/{libelle}")
    public List<FichierVocal> AfficherVocalParLibelleMinistere(@PathVariable String libelle) {
        return fichierVocalService.AfficherVocalParLibelleMinistere(libelle);
    }

    @ApiOperation(value = "Affichage des vocales par Id ministere")
    @GetMapping("/afficherVocalParIdMinistere/{id_ministere}")
    public Object AfficherVocalParIdMinistere(@PathVariable long id_ministere) {
        try {
            Ministere ministere = ministereRepository.findById(id_ministere).get();
            System.out.println(ministere);
            return fichierVocalService.AfficherVocalParIdMinistere(ministere);
        }catch (Exception e){
           return e.getMessage();
        }

    }

}
