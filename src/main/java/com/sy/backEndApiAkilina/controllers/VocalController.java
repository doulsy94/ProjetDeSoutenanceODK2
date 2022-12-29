package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.security.services.VocalService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vocal")
@AllArgsConstructor
public class VocalController {

    @Autowired
    final private VocalService vocalService;

    @DeleteMapping("/suprimer_vocal/{id_vocal}")
    public String delete(@PathVariable Long id_vocal){
        return this.vocalService.delete(id_vocal);
    }

}
