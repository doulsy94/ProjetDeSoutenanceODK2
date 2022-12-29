package com.sy.backEndApiAkilina.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sy.backEndApiAkilina.configuration.SaveImage;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.security.services.MinistereService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/api/ministere")
@RestController
@Api(value = "ministere", description = "MANIPULATION DES DONNEES DE LA TABLE MINISTERE")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class MinistereController {
    @Autowired
    MinistereService ministereService;

    @Autowired
    private MinistereRepository ministereRepository;

    @ApiOperation(value = "AJOUT DES DONNEES DANS LA TABLE MINISTERE")
    @PostMapping("/ajout_ministere")
    @PreAuthorize("hasRole('ADMIN')")
    public Object create(@RequestParam(value = "ministere") String minis,
                         @RequestParam(value = "file", required = true) MultipartFile file) {
        try {
            Ministere ministere = new JsonMapper().readValue(minis, Ministere.class);
            if (file != null) {
                System.out.println("ggggg");
                ministere.setImage(SaveImage.save("minstere", file, ministere.getLibelle()));
            }
            return ministereService.add(ministere);

        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @ApiOperation(value = "LIRE MINISTERE")
    @GetMapping("/lire_ministere")
    public List<Ministere> read() {
        return ministereService.read();
    }

    @ApiOperation(value = "LIRE MINISTERE")
    @GetMapping("/lire_ministere/{libelle}")
    public Ministere readMinistereParLibelle(@PathVariable("libelle") String libelle) {
        return ministereRepository.findByLibelle(libelle);
    }

    @ApiOperation(value = "MODIFICATION DES DONNEES DE LA TABLE MINISTERE")
    @PutMapping("/modifier_ministere/{id_ministere}")
    public Object update(@PathVariable Long id_ministere,
                         @RequestParam(value = "ministere") String minis,
                         @RequestParam(value = "file", required = true) MultipartFile file
    ) {
        try {
            Ministere ministere = new JsonMapper().readValue(minis, Ministere.class);
            if (file != null) {
                System.out.println("ggggg");
                ministere.setImage(SaveImage.save("ministere", file, ministere.getLibelle()));
            }
            return ministereService.update(id_ministere, ministere);

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @ApiOperation(value = "SUPPRESION DES DONNEE DANS LA TABLE MINISTERE")
    @DeleteMapping("/supprimer_ministere/{id_ministere}")
    public String delete(@PathVariable Long id_ministere) {
        return ministereService.delete(id_ministere);
    }
}

