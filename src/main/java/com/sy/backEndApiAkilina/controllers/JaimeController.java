package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.*;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.JaimeRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.JaimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RequestMapping("/api/jaime")
@RestController
@Api(value = "jaime", description = "MANIPULATION DES DONNEES DE LA TABLE J'AIME")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, maxAge = 3600, allowCredentials="true")

public class JaimeController {

   @Autowired
    private JaimeService jaimeService;
    @Autowired
    private IdeeRepository ideeRepository;
    @Autowired
    private JaimeRepository jaimeRepository;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Ajouter j'aime")
    @PostMapping("/ajouter/{id_user}/{id_idee}")
    public Object add(Jaime jaime, @PathVariable("id_user") Long id_user, @PathVariable("id_idee") Long id_idee) {
        try {
            User user= userRepository.findById(id_user).get();
            Idee idee = ideeRepository.findById(id_idee).get();

            jaime.setUser(user);
            jaime.setIdee(idee);
            return jaimeService.add(jaime, user, idee);
        }catch (Exception e){
           return e.getMessage();
        }

    }


    @ApiOperation(value = "Voir liste des j'aime")
    @GetMapping("/lire")
    public List<Jaime> read() {
        return jaimeService.read();
    }

    @ApiOperation(value = "SUPPRESION DES DONNEE DANS LA TABLE Jaime")
    @DeleteMapping("/supprimer/{id}/{id_user}")

    public String delete(@PathVariable Long id, @PathVariable Long id_user) {
        try {
            Jaime jaime = jaimeRepository.findById(id).get();

            if (jaime.getUser().getId_user() == id_user) {
                return jaimeService.delete(id);
            } else {
                return "vous n'etes pas autorisé à faire cette action";
            }
        }
        catch(Exception e){
                return e.getMessage();
            }
        }

    @ApiOperation(value = "Affichage des jaimes par Id idee")
    @GetMapping("/afficherJaimeParIdIdee/{id_idee}")
    public Object AfficherJaimeParIdIdee(@PathVariable long id_idee) {
        try {
            Idee idee = ideeRepository.findById(id_idee).get();
            System.out.println(idee);
            return jaimeService.AfficherJaimeParIdIdee(idee);
        }catch (Exception e){
            return e.getMessage();
        }

    }

   @ApiOperation(value = "Affichage du nombre des jaimes par Id idee")
    @GetMapping("/afficherNombreJaimeParIdIdee/{id_idee}")
    public Serializable AfficherNombreJaimeParIdIdee(@PathVariable long id_idee) {
        try {
            Idee idee = ideeRepository.findById(id_idee).get();
            System.out.println(idee);
            return (long) jaimeService.AfficherJaimeParIdIdee(idee).size();

        }catch (Exception e){
           return e.getMessage();
        }
          }

}


