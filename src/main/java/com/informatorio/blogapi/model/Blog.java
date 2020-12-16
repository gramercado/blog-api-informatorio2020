package com.informatorio.blogapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.nio.MappedByteBuffer;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity

public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private Usuario autor;

    @OneToMany(mappedBy = "blogReferente",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comentario> comentariosList = new ArrayList<>();

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

    //Getter y Setter

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

    public Date getFechaDeCreacionBlog() { return fechaDeCreacionBlog; }

    public void setFechaDeCreacionBlog(Date fechaDeCreacionBlog) {
        this.fechaDeCreacionBlog = fechaDeCreacionBlog;
    }

    public Boolean getPublicado() {
        return publicado;
    }

    public void setPublicado(boolean publicado) {
        this.publicado = publicado;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Blog{");
        sb.append("id=").append(id);
        sb.append(", autor=").append(autor);
        sb.append(", comentariosList=").append(comentariosList.toString());
        sb.append(", titulo='").append(titulo).append('\'');
        sb.append(", descripcion='").append(descripcion).append('\'');
        sb.append(", contenido='").append(contenido).append('\'');
        sb.append(", fechaDeCreacionBlog=").append(fechaDeCreacionBlog);
        sb.append(", publicado=").append(publicado);
        sb.append('}');
        return sb.toString();
    }
}



