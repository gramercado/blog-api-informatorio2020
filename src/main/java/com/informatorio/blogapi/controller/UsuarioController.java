package com.informatorio.blogapi.controller;

import com.informatorio.blogapi.model.Usuario;
import com.informatorio.blogapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.regex.Pattern;

@RequestMapping("/api/v1/usuario")
@RestController
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return new ResponseEntity<>(usuarioRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("/filtrarPorCiudad")
    public ResponseEntity<List<Usuario>> getFiltrarCiudad(@RequestParam String ciudad) {
        List<Usuario> listaUsuariosCiudad = usuarioRepository.getUsuariosPorCiudad(ciudad);

        if (listaUsuariosCiudad.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaUsuariosCiudad, HttpStatus.OK);
    }

    @GetMapping("/filtrarPorFecha")
    public ResponseEntity<List<Usuario>> getFiltrarFecha(@RequestParam String fecha) {
        List<Usuario> listaUsuariosFecha= usuarioRepository.getUsuariosPorFecha(fecha);

        if (listaUsuariosFecha.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(listaUsuariosFecha, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario nuevoUsuario) {

        nuevoUsuario.setFechaDeAlta(new Date());

        return new ResponseEntity<>(usuarioRepository.save(nuevoUsuario), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody @Valid Usuario usuarioNuevo) {
        Usuario usuarioViejo;
        Optional<Usuario> usuarioONull = usuarioRepository.findById(id);

        // si esta presente o no
        if (usuarioONull.isPresent()) {
            usuarioViejo = usuarioONull.get();
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String nombre = usuarioNuevo.getNombre();
        String apellido = usuarioNuevo.getApellido();
        String ciudad = usuarioNuevo.getCiudad();
        String provincia = usuarioNuevo.getProvincia();
        String pais = usuarioNuevo.getPais();

        if (nombre != null) {
            usuarioViejo.setNombre(nombre);
        }

        if (apellido != null) {
            usuarioViejo.setApellido(apellido);
        }

        if (ciudad != null) {
            usuarioViejo.setCiudad(ciudad);
        }

        if (provincia != null) {
            usuarioViejo.setProvincia(provincia);
        }

        if (pais != null) {
            usuarioViejo.setPais(pais);
        }

        return new ResponseEntity<>(usuarioRepository.save(usuarioViejo), HttpStatus.OK);
    }

    @PatchMapping("/actualizarEmail")
    public ResponseEntity<Void> actualizarEmail(@RequestParam Long id, @RequestParam String nuevoEmail) {
        if (nuevoEmail == null || !Pattern.matches("^([a-zA-Z!#$%&'*+-/=?^_`{|}~])+@([a-zA-Z])+\\.([a-zA-Z]){2,3}(\\.[a-zA-Z]{0,3})?$", nuevoEmail)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Usuario usuarioConNuevoEmail = usuarioRepository.getOne(id);
        usuarioConNuevoEmail.setEmail(nuevoEmail);

        usuarioRepository.save(usuarioConNuevoEmail);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/actualizarPassword/{id}")
    public ResponseEntity<Void> actualizarPassword(@PathVariable Long id, @RequestBody Map<String, String> jsonBody) {
        Optional<Usuario> optionalONull = usuarioRepository.findById(id);

        if (optionalONull.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String passwordExtraida = jsonBody.get("nuevaPassword");
        Usuario usuarioAModificar = optionalONull.get();
        usuarioAModificar.setPassword(passwordExtraida);

        usuarioRepository.save(usuarioAModificar);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> borrarUsuario(@PathVariable Long id) {
        Usuario usuarioABorrar = usuarioRepository.getOne(id);

        usuarioRepository.delete(usuarioABorrar);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
