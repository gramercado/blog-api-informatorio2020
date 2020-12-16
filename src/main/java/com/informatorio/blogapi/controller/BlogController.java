package com.informatorio.blogapi.controller;

import com.informatorio.blogapi.model.Blog;
import com.informatorio.blogapi.model.Usuario;
import com.informatorio.blogapi.repository.BlogRepository;
import com.informatorio.blogapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/v1/blog")
@RestController
public class BlogController {

    private final BlogRepository blogRepository;
    private final UsuarioRepository usuarioRepository;

    public BlogController(BlogRepository blogRepository, UsuarioRepository usuarioRepository) {
        this.blogRepository = blogRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Blog>> getBlog() {
        return new ResponseEntity<>(blogRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/filtrarPorPalabra")
    public ResponseEntity<List<Blog>> getFiltrarPorPalabra(@RequestParam String palabra) {
        List<Blog> listaBlogPalabra = blogRepository.getBlogContienePalabra(palabra);

        if (listaBlogPalabra.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaBlogPalabra, HttpStatus.OK);
    }

    @GetMapping("/filtrarPorNoPublicado")
    public ResponseEntity<List<Blog>> getFiltrarPorNoPublicado() {
        List<Blog> listaBlogSinPublicar = blogRepository.getBlogSinPublicar();

        if (listaBlogSinPublicar.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaBlogSinPublicar, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> crearBlog(@RequestBody Blog nuevoBlog, @RequestParam Long autor) {

        Optional<Usuario> autorEncontrado = usuarioRepository.findById(autor);

        if (autorEncontrado.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Usuario autorAGuardar = autorEncontrado.get();

        nuevoBlog.setAutor(autorAGuardar);
        nuevoBlog.setFechaDeCreacionBlog(new Date());
        nuevoBlog.setPublicado(true);

        Blog blogGuardado = blogRepository.saveAndFlush(nuevoBlog);

//        autorAGuardar.guardarBlog(blogGuardado);
//        usuarioRepository.saveAndFlush(autorAGuardar);

        return new ResponseEntity<>(blogGuardado, HttpStatus.CREATED);
    }
    // Falta @PutMapping()

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> borrarBlog(@PathVariable Long id) {
        Blog blogABorrar = blogRepository.getOne(id);

        blogRepository.delete(blogABorrar);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
