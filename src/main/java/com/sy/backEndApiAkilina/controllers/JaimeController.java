package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.BadWord;
import com.sy.backEndApiAkilina.models.Jaime;
import com.sy.backEndApiAkilina.security.services.BadWordService;
import com.sy.backEndApiAkilina.security.services.JaimeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/jaime")
@RestController
@Api(value = "jaime", description = "MANIPULATION DES DONNEES DE LA TABLE J'AIME")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class JaimeController {

   @Autowired
    private JaimeService jaimeService;
    @ApiOperation(value = "Ajouter j'aime")
    @PostMapping("/ajouter")
    public Jaime add(@RequestBody Jaime jaime) {
        return jaimeService.add(jaime);
    }


    @ApiOperation(value = "Voir liste des j'aime")
    @GetMapping("/lire")
    public List<Jaime> read() {
        return jaimeService.read();
    }

    @ApiOperation(value = "SUPPRESION DES DONNEE DANS LA TABLE Jaime")
    @DeleteMapping("/supprimer/{id}")

    public String delete(@PathVariable Long id) {
        return jaimeService.delete(id);
    }

}


