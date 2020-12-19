package com.informatorio.blogapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler", "comentariosList"})
public class Blog implements Comparable<Blog> {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Blog)) return false;
        Blog blog = (Blog) o;
        return getPublicado() == blog.getPublicado() && getId().equals(blog.getId()) && getAutor().equals(blog.getAutor()) && Objects.equals(getComentariosList(), blog.getComentariosList()) && getTitulo().equals(blog.getTitulo()) && getDescripcion().equals(blog.getDescripcion()) && getContenido().equals(blog.getContenido()) && getFechaDeCreacionBlog().equals(blog.getFechaDeCreacionBlog());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAutor(), getComentariosList(), getTitulo(), getDescripcion(), getContenido(), getFechaDeCreacionBlog(), getPublicado());
    }

    @Override
    public int compareTo(Blog otroBlog) {
        if (this.fechaDeCreacionBlog.after(otroBlog.fechaDeCreacionBlog)) {
            return 1;
        } else if (this.fechaDeCreacionBlog.before(otroBlog.fechaDeCreacionBlog)) {
            return -1;
        } else {
            return 0;
        }
    }
}