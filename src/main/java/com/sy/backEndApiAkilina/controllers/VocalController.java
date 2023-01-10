package com.sy.backEndApiAkilina.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sy.backEndApiAkilina.models.*;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.VocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Api(value = "vocal", description = "MANIPULATION DES DONNEES DE LA TABLE VOCAL")
@RestController
@RequestMapping("/api/vocal")
@AllArgsConstructor
public class VocalController {

    @Autowired
    final private VocalService vocalService;

    private final UserRepository userRepository;

    private final MinistereRepository ministereRepository;

   /* @ApiOperation(value = "Ajout de vocal")
    @PostMapping("/ajouter_vocal")
    public Vocal add(@RequestBody Vocal vocal) {
        return vocalService.add(vocal);
    }*/

    @ApiOperation(value = "Ajout de vocal")
    @PostMapping("/ajouter_vocal")
    public void add(@RequestParam("vocal_contenu") String voc,
                                 @RequestParam("fichier") MultipartFile fichier) throws IOException {
        //==================Commentaires=======================

        Vocal vocal = new JsonMapper().readValue(voc, Vocal.class);

        long id=2;
        long iduser = 1;

        User us =(User) userRepository.findById(iduser).get();
        Ministere ministere = (Ministere) ministereRepository.findById(id).get();

        vocal.setId_ministere(ministere);
        vocal.setId_user(us);

        vocalService.add(fichier.getBytes());
    }

    @ApiOperation(value = "Lire vocal de l'utilisateur")
    @GetMapping("/lire_vocal")
    public List<Vocal> read() {
        return vocalService.read();
    }

    @ApiOperation(value = "Supprimer vocal")
    @DeleteMapping("/suprimer_vocal/{id_vocal}")
    public String delete(@PathVariable Long id_vocal) {
        return this.vocalService.delete(id_vocal);
    }

    @ApiOperation(value = "Affichage des vocales par ministere")
    @GetMapping("/afficherVocalParMinistere/{id_ministere}")
    public List<Vocal> AfficherIdeeParMinistere(@PathVariable long id_ministere) {
        return vocalService.AfficherVocalParMinistere(id_ministere);

    }
}