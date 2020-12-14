package com.informatorio.blogapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.nio.MappedByteBuffer;
import java.security.KeyStore;
import java.util.Date;
import java.util.List;

@Entity
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String titulo;

    @Column
    private String descripcion;

    @Column
    private String contenido;

    @Column
    private Date fechaDeCreacionBlog;

    @Column
    private Boolean publicado;

    @ManyToOne
    @JoinColumn(name = "autor", referencedColumnName = "id")
    private Usuario autor;

}



