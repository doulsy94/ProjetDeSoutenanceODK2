package com.sy.backEndApiAkilina.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "commentaire")
@Getter
@Setter
@NoArgsConstructor
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_commentaire;

    private String contenu_commentaire;

    @ManyToOne()
    private User user;

    @ManyToOne()
    private Idee idee;
}
