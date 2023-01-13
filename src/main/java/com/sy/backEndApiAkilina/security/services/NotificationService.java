package com.sy.backEndApiAkilina.security.services;

import com.sy.backEndApiAkilina.models.Notification;
import java.util.List;

public interface NotificationService {

    //methode permettant de creer un notification
    Notification add(Notification notification);

    //methode permettant de lire notification
    List<Notification> read();

    //methode permettant de de supprimer une notification
    String delete(Long id_notification);

    Notification getById(Long id_notification);






}
