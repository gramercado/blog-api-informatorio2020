package com.informatorio.blogapi.repository;

import com.informatorio.blogapi.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BlogRepository extends JpaRepository<Blog, Long> {

    List<Blog> findByTituloContaining(String palabra);

    List<Blog> findByPublicadoFalse();
}
