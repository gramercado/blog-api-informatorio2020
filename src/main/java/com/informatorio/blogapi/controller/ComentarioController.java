package com.informatorio.blogapi.controller;

import com.informatorio.blogapi.model.Blog;
import com.informatorio.blogapi.model.Comentario;
import com.informatorio.blogapi.model.Usuario;
import com.informatorio.blogapi.repository.BlogRepository;
import com.informatorio.blogapi.repository.ComentarioRepository;
import com.informatorio.blogapi.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;


@RequestMapping("/api/v1/comentario")
@RestController
public class ComentarioController {

    private final ComentarioRepository comentarioRepository;
    private final BlogRepository blogRepository;
    private final UsuarioRepository usuarioRepository;

    public ComentarioController(ComentarioRepository comentarioRepository, BlogRepository blogRepository, UsuarioRepository usuarioRepository) {
        this.comentarioRepository = comentarioRepository;
        this.blogRepository = blogRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Comentario>> getComentario() {
        return new ResponseEntity<>(comentarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/filtrarPorBlog")
    public ResponseEntity<List<Comentario>> getFiltrarPorBlog(@RequestParam Long blogId, @RequestParam Integer max) {
        List<Comentario> listaComentarioBlog = comentarioRepository.findComentarioByBlog_Id(blogId, max);

        if (listaComentarioBlog.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaComentarioBlog, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> crearComentario(@RequestBody Comentario nuevoComentario, @RequestParam Long autor, @RequestParam Long blog) {
        Optional<Usuario> autorEncontrado = usuarioRepository.findById(autor);

        if (autorEncontrado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Optional<Blog> blogEncontrado = blogRepository.findById(blog);

        if (blogEncontrado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Usuario autorAGuardar = autorEncontrado.get();
        Blog blogAGuardar = blogEncontrado.get();

        System.out.println(nuevoComentario.getCuerpo());

        nuevoComentario.setAutor(autorAGuardar);
        nuevoComentario.setBlogReferente(blogAGuardar);
        nuevoComentario.setFechaDeCreacion(new Date());

        Comentario comentarioGuardado = comentarioRepository.save(nuevoComentario);

        return new ResponseEntity<>(comentarioGuardado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarComentario(@PathVariable Long id, @RequestBody Comentario comentarioNuevo) {
        Comentario comentarioViejo;
        Optional<Comentario> comentarioONull = comentarioRepository.findById(id);

        // si esta presente o no
        if (comentarioONull.isPresent()) {
            comentarioViejo = comentarioONull.get();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String cuerpo = comentarioNuevo.getCuerpo();

        if (cuerpo != null) {
            comentarioViejo.setCuerpo(cuerpo);
        }

        return new ResponseEntity<>(comentarioRepository.save(comentarioViejo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> borrarBlog(@PathVariable Long id) {
        Comentario comentarioABorrar = comentarioRepository.getOne(id);

        comentarioRepository.delete(comentarioABorrar);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

