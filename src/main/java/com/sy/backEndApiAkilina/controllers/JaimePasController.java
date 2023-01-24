package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.JaimePas;
import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.JaimePasRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.JaimePasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RequestMapping("/api/jaimepas")
@RestController
@Api(value = "jaimepas", description = "MANIPULATION DES DONNEES DE LA TABLE J'AIME Pas")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
public class JaimePasController {

    @Autowired
    private JaimePasService jaimePasService;

    @Autowired
    private JaimePasRepository jaimePasRepository;

    @Autowired
    private IdeeRepository ideeRepository;
    @Autowired
    private UserRepository userRepository;

    @ApiOperation(value = "Ajouter j'aime pas")
    @PostMapping("/ajouter/{id_user}/{id_idee}")
    public Object add(JaimePas jaimePas, @PathVariable("id_user") Long id_user, @PathVariable("id_idee") Long id_idee) {

        try {
            User user = userRepository.findById(id_user).get();
            Idee idee = ideeRepository.findById(id_idee).get();

            jaimePas.setUser(user);
            jaimePas.setIdee(idee);
            return jaimePasService.add(jaimePas, user, idee);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @ApiOperation(value = "Voir liste des j'aime pas")
    @GetMapping("/lire")
    public List<JaimePas> read() {
        return jaimePasService.read();
    }

    @ApiOperation(value = "SUPPRESION DES DONNEE DANS LA TABLE Jaime Pas")
    @DeleteMapping("/supprimer/{id}/{id_user}")

    public String delete(@PathVariable Long id, @PathVariable Long id_user) {
    try {
        JaimePas jaimePas = jaimePasRepository.findById(id).get();

        if(jaimePas.getUser().getId_user()== id_user){
            return jaimePasService.delete(id);
        }else {
            return "vous n'etes pas autorisé à faire cette action";
        }
        }catch (Exception e){
            return e.getMessage();
            }
    }

    @ApiOperation(value = "Affichage des jaimesPas par Id idee")
    @GetMapping("/afficherJaimePasParIdIdee/{id_idee}")
    public Object AfficherJaimePasParIdIdee(@PathVariable long id_idee) {
        try {
            Idee idee = ideeRepository.findById(id_idee).get();
            System.out.println(idee);
            return jaimePasService.AfficherJaimePasParIdIdee(idee);
        }catch (Exception e){
            return e.getMessage();
        }

    }

    @ApiOperation(value = "Affichage du nombre des jaimes pas par Id idee")
    @GetMapping("/afficherNombreJaimePasParIdIdee/{id_idee}")
    public Serializable AfficherNombreJaimePasParIdIdee(@PathVariable long id_idee) {
        try {
            Idee idee = ideeRepository.findById(id_idee).get();
            System.out.println(idee);
            return (long) jaimePasService.AfficherJaimePasParIdIdee(idee).size();

        }catch (Exception e){
            return e.getMessage();
        }
         }

}

