package com.sy.backEndApiAkilina.controllers;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.sy.backEndApiAkilina.configuration.SaveImage;
import com.sy.backEndApiAkilina.models.ERole;
import com.sy.backEndApiAkilina.models.Ministere;
import com.sy.backEndApiAkilina.models.Role;
import com.sy.backEndApiAkilina.models.User;
import com.sy.backEndApiAkilina.repository.MinistereRepository;
import com.sy.backEndApiAkilina.repository.RoleRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.MinistereService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/ministere")
@RestController
@Api(value = "ministere", description = "MANIPULATION DES DONNEES DE LA TABLE MINISTERE")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class MinistereController {
    @Autowired
    MinistereService ministereService;

    @Autowired
    private MinistereRepository ministereRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @ApiOperation(value = "AJOUT DES DONNEES DANS LA TABLE MINISTERE")
    @PostMapping("/ajouter")

    public Object add(@RequestParam(value = "ministere") String minis,
                         @RequestParam(value = "file", required = true) MultipartFile file) {
        Role role = roleRepository.findByName(ERole.ROLE_ADMIN);
        User admin = userRepository.findByRoles(role);
        try {
            Ministere ministere = new JsonMapper().readValue(minis, Ministere.class);
            if (file != null) {
                System.out.println("ggggg");
                ministere.setImage(SaveImage.save("minstere", file, ministere.getLibelle()));
            }
            ministere.setUser(admin);
            return ministereService.add(ministere);

        } catch (Exception e) {
            return e.getMessage();
        }
    }
    @ApiOperation(value = "LIRE MINISTERE")
    @GetMapping("/lire")
    public List<Ministere> read() {
        return ministereService.read();
    }

    @ApiOperation(value = "Nombre de ministere")
    @GetMapping("/afficher_ministere_nombre")
    public int readNombre() {return ministereService.read().size();
    }


    @ApiOperation(value = "LIRE MINISTERE Par libelle")
    @GetMapping("/lireParLibelle/{libelle}")
    public Ministere readMinistereParLibelle(@PathVariable("libelle") String libelle) {
        return ministereRepository.findByLibelle(libelle);
    }

    @ApiOperation(value = "LIRE MINISTERE Par ID")
    @GetMapping("/lireParId/{id_ministere}")
    public Optional<Ministere> readMinistereParID(@PathVariable("id_ministere") Long id_ministere) {
        return ministereRepository.findById(id_ministere);
    }

    @ApiOperation(value = "MODIFICATION DES DONNEES DE LA TABLE MINISTERE")
    @PutMapping("/modifier/{id_ministere}")

    public Object update(@PathVariable Long id_ministere,
                         @RequestParam(value = "ministere") String minis,
                         @RequestParam(value = "file", required = false) MultipartFile file
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
    @DeleteMapping("/supprimer/{id_ministere}")

    public String delete(@PathVariable Long id_ministere) {
        return ministereService.delete(id_ministere);
    }
}

