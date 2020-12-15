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

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    @OneToMany(mappedBy = "blogReferente")
    private List<Comentario> comentariosList;

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
    private boolean publicado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario autor) {
        this.autor = autor;
    }

    public List<Comentario> getComentariosList() {
        return comentariosList;
    }

    public void setComentariosList(List<Comentario> comentariosList) {
        this.comentariosList = comentariosList;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Date getFechaDeCreacionBlog() {
        return fechaDeCreacionBlog;
    }

    public void setFechaDeCreacionBlog(Date fechaDeCreacionBlog) {
        this.fechaDeCreacionBlog = fechaDeCreacionBlog;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }
}



