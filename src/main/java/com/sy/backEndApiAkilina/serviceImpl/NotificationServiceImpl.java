package com.sy.backEndApiAkilina.serviceImpl;

import com.sy.backEndApiAkilina.models.Notification;
import com.sy.backEndApiAkilina.repository.NotificationRepository;
import com.sy.backEndApiAkilina.security.services.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class NotificationServiceImpl implements NotificationService {


    private final NotificationRepository notificationRepository;


    @Override
    public Notification add(Notification notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public List<Notification> read() {
        return notificationRepository.findAll();
    }

    @Override
    public String delete(Long id_notification) {
        notificationRepository.deleteById(id_notification);
        return "Notification supprimé avec succès";
    }

    @Override
    public Notification getById(Long id_notification) {
        return notificationRepository.findById(id_notification).get();
    }

    @Override
    public Notification getByTitre(String titre) {
        return notificationRepository.findByTitre(titre);
    }
}
