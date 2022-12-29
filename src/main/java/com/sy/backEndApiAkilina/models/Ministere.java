package com.sy.backEndApiAkilina.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ministere")
@Getter
@Setter
@NoArgsConstructor
public class Ministere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_ministere;

    @Column(unique = true)
    private String libelle;

    private String image;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User id_user;
}
