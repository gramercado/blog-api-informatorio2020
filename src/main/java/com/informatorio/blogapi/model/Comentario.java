package com.informatorio.blogapi.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.Date;

@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Date fechaDeCreacion;

    @Column
    @Max(value = 200)
    private String cuerpo;

    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private Usuario autor;
}
