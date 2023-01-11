package com.sy.backEndApiAkilina.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notification;

    private  String titre;
    private Date datenotif;
    private String description;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_idee")
    private Idee id_idee;
}
