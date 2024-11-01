package com.assembleia.votacao.controller;

import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

   @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario) throws IllegalAccessException {
       return  usuarioService.create(usuario);
   }

   @GetMapping("/{id}")
    public Usuario buscaUsuario(@PathVariable  Long id){
       return usuarioService.buscarId(id);
   }

}
