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

    private byte[] contenu_vocal;

    public Vocal(byte[] contenu_vocal){
        this.contenu_vocal = contenu_vocal;
    }

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_user")
    private User id_user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "id_ministere")
    private Ministere id_ministere;
}
