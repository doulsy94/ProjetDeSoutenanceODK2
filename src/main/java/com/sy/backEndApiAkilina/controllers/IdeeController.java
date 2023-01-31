package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.configuration.ResponseMessage;
import com.sy.backEndApiAkilina.models.*;
import com.sy.backEndApiAkilina.repository.*;
import com.sy.backEndApiAkilina.security.services.CommentaireService;
import com.sy.backEndApiAkilina.security.services.IdeeService;
import com.sy.backEndApiAkilina.security.services.WordFilterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Api(value = "idee", description = "MANIPULATION DES DONNEES DE LA TABLE IDEE")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:8100"}, maxAge = 3600, allowCredentials="true")
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

    private final RoleRepository roleRepository;

    private final WordFilterService wordFilterService;

    @ApiOperation(value = "Ajout d'idée")
    @PostMapping("/ajouter/{id_user}/{id_ministere}")
    public String add(@RequestBody Idee idee, @PathVariable("id_user") Long id_user, @PathVariable("id_ministere") Long id_ministere) {
        try {
            User user = userRepository.findById(id_user).get();
            Ministere ministere = ministereRepository.findById(id_ministere).get();
            idee.setUser(user);
            idee.setMinistere(ministere);

            return ideeService.add(idee, user, ministere);

        } catch (Exception e) {
            return "Veuillez utilisez des mots approprié";
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
    public int readNombre() {
        return ideeService.read().size();
    }

    @ApiOperation(value = "Modifier idée de l'utilisateur")
    @PostMapping("/modifier/{id_idee}/{id_user}")
    public String update(@PathVariable Long id_idee, @PathVariable Long id_user, @RequestBody Idee idee) {
        try {
            Idee idee1 = ideeRepository.findById(id_idee).get();

            if (idee1.getUser().getId_user() == id_user) {
                return ideeService.update(id_idee, idee);
            } else {
                return "vous n'etes pas autorisé à faire cette action";
            }
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @ApiOperation(value = "Supprimer idée de l'utilisateur")
    @DeleteMapping("/supprimer/{id_idee}/{id_user}")
    public String delete(@PathVariable(value = "id_idee") Long id_idee, @PathVariable(value = "id_user") Long id_user) {
        try {
            Idee idee = ideeRepository.findById(id_idee).get();

            User user = userRepository.findById(id_user).get();

            if (user.getRoles().contains(roleRepository.findByName(ERole.ROLE_ADMIN))) {
                ideeService.delete(id_idee);
                return "idee supprimee par l'admin avec succes! ";
            } else if (idee.getUser().getId_user() == id_user) {
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
        } catch (Exception e) {
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
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @ApiOperation(value = "LIRE IDEE Par ID")
    @GetMapping("/lireParId/{id_idee}")
    public Object readIdeeParID(@PathVariable("id_idee") Long id_idee) {
        try {
            return ideeRepository.findById(id_idee);
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    @ApiOperation(value = "Faire like")
    @PostMapping("/like/{id_user}/{id_idee}")
    public ResponseEntity<?> likeIdee(@PathVariable("id_user") Long id_user, @PathVariable("id_idee") Long id_idee){

        Idee idee = ideeService.trouverIdeeParID((id_idee));
        if (idee == null) {
            return new ResponseEntity<>("Idee not found", HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findById(id_user).get();
        if (user == null){
            return new ResponseEntity<>("User not found",  HttpStatus.NOT_FOUND);
        }
        try {
            idee.setLikes(1);
            List<Idee> ideeList = user.getLikedIdee();
            ideeList.add(idee);
            user.setLikedIdee(ideeList);
            userRepository.save(user);
            return new ResponseEntity<>(idee, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("can't like idee", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Faire unlike")
    @PostMapping("/unlike/{id_user}/{id_idee}")
    public ResponseEntity<?> unlikeIdee(@PathVariable("id_user") Long id_user, @PathVariable("id_idee") Long id_idee){

        Idee idee = ideeService.trouverIdeeParID(id_idee);
        if (idee == null) {
            return new ResponseEntity<>("Idee not found", HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findById(id_user).get();
        if (user == null){
            return new ResponseEntity<>("User not found",  HttpStatus.NOT_FOUND);
        }
        try {
            idee.setLikes(idee.getLikes()-1);
            List<Idee> ideeList = user.getLikedIdee();
            ideeList.add(idee);
            user.getLikedIdee().remove(idee);
            userRepository.save(user);
            return new ResponseEntity<>(idee, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("can't unlike idee", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Faire dislike")
    @PostMapping("/dislike/{id_user}/{id_idee}")
    public ResponseEntity<?> disLikeIdee(@PathVariable("id_user") Long id_user, @PathVariable("id_idee") Long id_idee){

        Idee idee = ideeService.trouverIdeeParID(id_idee);
        if (idee == null) {
            return new ResponseEntity<>("Idee not found", HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findById(id_user).get();
        if (user == null){
            return new ResponseEntity<>("User not found",  HttpStatus.NOT_FOUND);
        }
        try {
            idee.setDislikes(1);
            List<Idee> ideeList = user.getDislikedIdee();
            ideeList.add(idee);
            user.setDislikedIdee(ideeList);
            userRepository.save(user);
            return new ResponseEntity<>(idee, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("can't dislike idee", HttpStatus.BAD_REQUEST);
        }
    }

    @ApiOperation(value = "Faire disunlike")
    @PostMapping("/disunlike/{id_user}/{id_idee}")
    public ResponseEntity<?> disunlikeIdee(@PathVariable("id_user") Long id_user, @PathVariable("id_idee") Long id_idee){

        Idee idee = ideeService.trouverIdeeParID(id_idee);
        if (idee == null) {
            return new ResponseEntity<>("Idee not found", HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findById(id_user).get();
        if (user == null){
            return new ResponseEntity<>("User not found",  HttpStatus.NOT_FOUND);
        }
        try {
            idee.setDislikes(idee.getDislikes()-1);
            List<Idee> ideeList = user.getDislikedIdee();
            ideeList.add(idee);
            user.getDislikedIdee().remove(idee);
            userRepository.save(user);
            return new ResponseEntity<>(idee, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("can't disunlike idee", HttpStatus.BAD_REQUEST);
        }
    }







   /* @PatchMapping("/etat/{id_idee}")
    public ResponseMessage SetEtat(@RequestBody Idee idee, @PathVariable("id_idee") Long id_idee) {
        if (this.ideeRepository.findById(id_idee) == null) {

            ResponseMessage message = new ResponseMessage("Idee n'existe pas !", false);
            return message;
        } else {


            return this.ideeService.SetEtat(idee, id_idee);
        }

    }*/
}

