package com.informatorio.blogapi.controller;

import com.informatorio.blogapi.model.Blog;
import com.informatorio.blogapi.model.Usuario;
import com.informatorio.blogapi.repository.BlogRepository;
import com.informatorio.blogapi.repository.UsuarioRepository;
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
        List<Blog> listaBlogPalabra = blogRepository.findByTituloContaining(palabra);

        if (listaBlogPalabra.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaBlogPalabra, HttpStatus.OK);
    }

    @GetMapping("/filtrarPorNoPublicado")
    public ResponseEntity<List<Blog>> getFiltrarPorNoPublicado() {
        List<Blog> listaBlogSinPublicar = blogRepository.findByPublicadoFalse();

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

        return new ResponseEntity<>(blogGuardado, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarBlog(@PathVariable Long id, @RequestBody Blog blogNuevo) {
        Blog blogViejo;
        Optional<Blog> blogONull = blogRepository.findById(id);

        // si esta presente o no
        if (blogONull.isPresent()) {
            blogViejo = blogONull.get();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String titulo = blogNuevo.getTitulo();
        String descripcion = blogNuevo.getDescripcion();
        String contenido = blogNuevo.getContenido();

        if (titulo != null) {
            blogViejo.setTitulo(titulo);
        }

        if (descripcion != null) {
            blogViejo.setDescripcion(descripcion);
        }

        if (contenido != null) {
            blogViejo.setContenido(contenido);
        }

       return new ResponseEntity<>(blogRepository.save(blogViejo), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> actualizaraNopublicado(@PathVariable Long id) {

        Optional<Blog> optionalONull = blogRepository.findById(id);

        if (optionalONull.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Blog blogAModificar = optionalONull.get();
        blogAModificar.setPublicado(false);

        blogRepository.save(blogAModificar);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> borrarBlog(@PathVariable Long id) {
        Blog blogABorrar = blogRepository.getOne(id);

        blogRepository.delete(blogABorrar);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
