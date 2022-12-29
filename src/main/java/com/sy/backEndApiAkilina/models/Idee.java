package com.sy.backEndApiAkilina.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "idee")
@Getter
@Setter
@NoArgsConstructor
public class Idee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_idee;
    private String contenu_idee;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User id_user;

    @ManyToOne
    @JoinColumn(name = "id_ministere")
    private Ministere id_ministere;
}
