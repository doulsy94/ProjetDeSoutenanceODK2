package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.BadWord;
import com.sy.backEndApiAkilina.repository.BadWordRepository;
import com.sy.backEndApiAkilina.security.services.BadWordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/badword")
@RestController
@Api(value = "badword", description = "MANIPULATION DES DONNEES DE LA TABLE BadWord")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class BadWordController {

    @Autowired
    private BadWordService badWordService;
    @Autowired
    private BadWordRepository badWordRepository;

    @ApiOperation(value = "Ajouter BadWord")
    @PostMapping("/ajouter")
    public BadWord add(@RequestBody BadWord badWord) {
        return badWordService.add(badWord);
    }


    @ApiOperation(value = "LIRE BadWord")
    @GetMapping("/lire")
    public List<BadWord> read() {
        return badWordService.read();
    }

    @ApiOperation(value = "SUPPRESION DES DONNEE DANS LA TABLE BadWord")
    @DeleteMapping("/supprimer/{id}")

    public String delete(@PathVariable Long id) {
        return badWordService.delete(id);
    }

}
