package com.assembleia.votacao.controller;

import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity getAllClients(){
        var allClients = usuarioService.getAll();
        return ResponseEntity.ok(allClients);
    }

    @PostMapping ("/create") ResponseEntity createClients(@RequestBody Usuario usuario){
        return usuarioService.create(usuario);
    }

}
