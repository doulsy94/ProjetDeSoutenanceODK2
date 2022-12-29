package com.sy.backEndApiAkilina.repository;

import com.sy.backEndApiAkilina.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificatonRepository extends JpaRepository<Notification, Long> {
}
