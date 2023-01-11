package com.sy.backEndApiAkilina.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "bad_words")
@NoArgsConstructor
public class BadWord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String word;

    public BadWord(Long o, String impoli) {
        this.id = o;
        this.word = impoli;
    }
}
