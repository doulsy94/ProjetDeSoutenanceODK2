package com.sy.backEndApiAkilina.controllers;

import com.sy.backEndApiAkilina.models.Notification;
import com.sy.backEndApiAkilina.repository.IdeeRepository;
import com.sy.backEndApiAkilina.repository.UserRepository;
import com.sy.backEndApiAkilina.security.services.NotificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/notification")
@RestController
@Api(value = "notification", description = "MANIPULATION DES DONNEES DE LA TABLE NOTIFICATION")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IdeeRepository ideeRepository;

    @ApiOperation(value = "Affichage de notification")
    @GetMapping("/afficher")
    public List<Notification> read() {
        return notificationService.read();
    }

    @ApiOperation(value = "Supprimer notification")
    @DeleteMapping("/suprimer /{id_notification}")
    public String delete(@PathVariable Long id_notification){
        return this.notificationService.delete(id_notification);
    }


}
