package com.informatorio.blogapi.repository;

import com.informatorio.blogapi.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long> {

    @Query(value="SELECT * FROM BLOG WHERE TITULO LIKE ?1",nativeQuery=true)
    List<Blog> getBlogContienePalabra(String palabra);
// Faltan los % que me dan error

    @Query(value="SELECT * FROM BLOG WHERE PUBLICADO = FALSE", nativeQuery = true)
    List<Blog> getBlogSinPublicar();
}
