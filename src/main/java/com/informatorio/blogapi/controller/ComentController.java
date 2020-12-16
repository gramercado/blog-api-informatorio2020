package com.informatorio.blogapi.controller;

import com.informatorio.blogapi.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/coment")
@RestController
public class ComentController {
    private final ComentarioRepository comentarioRepository;

    @Autowired
    public ComentController(ComentarioRepository comentarioRepository) {
        this.comentarioRepository = comentarioRepository;
    }

}
