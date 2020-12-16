package com.informatorio.blogapi.repository;

import com.informatorio.blogapi.model.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface ComentarioRepository extends JpaRepository<Comentario, Long>{
    //@Query(value="SELECT * FROM COMENTARIO WHERE ... ?1",nativeQuery=true)
    //List<Comentario> getComentariodeBlog(String id);
}
