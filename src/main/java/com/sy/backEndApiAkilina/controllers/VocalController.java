package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.Commentaire;
import com.sy.backEndApiAkilina.models.Idee;
import com.sy.backEndApiAkilina.models.Vocal;
import com.sy.backEndApiAkilina.security.services.VocalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value = "vocal", description = "MANIPULATION DES DONNEES DE LA TABLE VOCAL")
@RestController
@RequestMapping("/api/vocal")
@AllArgsConstructor
public class VocalController {

    @Autowired
    final private VocalService vocalService;

    @ApiOperation(value = "Ajout de vocal")
    @PostMapping("/ajouter_vocal")
    public Vocal add(@RequestBody Vocal vocal) {
        return vocalService.add(vocal);
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