package com.sy.backEndApiAkilina.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "vocal")
@Getter
@Setter
@NoArgsConstructor
public class Vocal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_vocal;

    private String contenu_vocal;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User id_user;
}
