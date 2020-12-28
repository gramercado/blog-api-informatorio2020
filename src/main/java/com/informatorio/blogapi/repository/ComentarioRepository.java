package com.informatorio.blogapi.repository;

import com.informatorio.blogapi.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ComentarioRepository extends JpaRepository<Comentario, Long>{

    @Query(value = "SELECT * FROM COMENTARIO WHERE BLOG_ID = ?1 LIMIT ?2", nativeQuery = true)
    List <Comentario> findComentarioByBlog_Id(Long blogId, Integer max);
}
