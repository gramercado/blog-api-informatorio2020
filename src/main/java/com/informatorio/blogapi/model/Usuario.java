package com.informatorio.blogapi.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler", "blogs", "comentarios"})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private final List<Blog> blogs = new ArrayList<>();

    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comentario> comentarios = new ArrayList<>();

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column(unique = true)
    @Email
    private String email;

    @Column
    @NotNull
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column
    private Date fechaDeAlta;

    @Column
    private String ciudad;

    @Column
    private String provincia;

    @Column
    private String pais;

    // Getter y Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void guardarBlog(Blog blogAgregado) {
        this.blogs.add(blogAgregado);
    }

    public List<Blog> getBlogs() {
        return blogs;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFechaDeAlta() {
        return fechaDeAlta;
    }

    public void setFechaDeAlta(Date fechaDeAlta) {
        this.fechaDeAlta = fechaDeAlta;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Usuario{");
        sb.append("id=").append(id);
        sb.append(", blogs=").append(blogs);
        sb.append(", comentarios=").append(comentarios);
        sb.append(", nombre='").append(nombre).append('\'');
        sb.append(", apellido='").append(apellido).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", fechaDeAlta=").append(fechaDeAlta);
        sb.append(", ciudad='").append(ciudad).append('\'');
        sb.append(", provincia='").append(provincia).append('\'');
        sb.append(", pais='").append(pais).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
