package com.assembleia.votacao.controller;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
        var senhahash = BCrypt.withDefaults().hashToString(12,usuario.getSenha().toCharArray());
        usuario.setSenha(senhahash);
       return  usuarioService.create(usuario);
   }


    @GetMapping("/{id}")
    public Usuario buscaUsuario(@PathVariable  Long id){
       return usuarioService.buscarId(id);
   }

     @DeleteMapping("/{id}")
    public void deletaUsuario(@PathVariable Long id) {
         usuarioService.delete(id);
    }

}
