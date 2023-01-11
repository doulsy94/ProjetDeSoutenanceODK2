package com.sy.backEndApiAkilina.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "idee")
@Getter
@Setter
@NoArgsConstructor
public class Idee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_idee;

    private String contexte;
    private String contenu_idee;

    private Date date;


    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user")
    private User id_user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_ministere")
    private Ministere id_ministere;

}
