package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.JaimePas;
import com.sy.backEndApiAkilina.security.services.JaimePasService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/jaimepas")
@RestController
@Api(value = "jaimepas", description = "MANIPULATION DES DONNEES DE LA TABLE J'AIME Pas")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class JaimePasController {

    @Autowired
    private JaimePasService jaimePasService;
    @ApiOperation(value = "Ajouter j'aime pas")
    @PostMapping("/ajouter")
    public JaimePas add(@RequestBody JaimePas jaimePas) {
        return jaimePasService.add(jaimePas);
    }


    @ApiOperation(value = "Voir liste des j'aime")
    @GetMapping("/lire")
    public List<JaimePas> read() {
        return jaimePasService.read();
    }

    @ApiOperation(value = "SUPPRESION DES DONNEE DANS LA TABLE Jaime Pas")
    @DeleteMapping("/supprimer/{id}")

    public String delete(@PathVariable Long id) {
        return jaimePasService.delete(id);
    }

}

