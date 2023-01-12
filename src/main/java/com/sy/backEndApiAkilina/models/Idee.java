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


    @ManyToOne()
    //@JoinColumn(name = "id_user")
    private User user;

    @ManyToOne()
    //@JoinColumn(name = "id_ministere")
    private Ministere ministere;


}
