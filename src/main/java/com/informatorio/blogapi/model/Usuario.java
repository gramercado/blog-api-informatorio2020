package com.informatorio.blogapi.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(unique = true)
    @Email
    private String email;

    @Column
    private String contrasenia;

    @Column
    private Date fechaDeAlta;

    @Column
    private String ciudad;

    @Column
    private String provincia;

    @Column
    private String pais;

}
