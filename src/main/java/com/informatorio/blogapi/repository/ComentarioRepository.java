package com.informatorio.blogapi.repository;

import com.informatorio.blogapi.model.Blog;
import com.informatorio.blogapi.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

    @Query(value="SELECT CUERPO, FECHA_DE_CREACION FROM Comentario INNER JOIN Blog ON COMENTARIO.BLOG_ID = ?1", nativeQuery=true)
    List<Comentario> findComentarioByBlog(Long id);



}
