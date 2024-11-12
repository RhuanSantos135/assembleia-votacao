package com.assembleia.votacao.controller;

import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Criar usuario", description = "Essa função tem como base criar um usuario.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                    description = "Usuario registado com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Usuario.class))),
            @ApiResponse(responseCode = "400",
                    description = "O campo de nome é obrigatório e não pode estar vazio. || O usuário informado já está cadastrado no sistema.")})
    @PostMapping
    public Usuario criarUsuario(@RequestBody Usuario usuario){
       return  usuarioService.create(usuario);
   }

    @Operation(summary = "Obter usuario por ID", description = "Essa função tem como objetivo acessar os atributos do usuario por ID")
    @GetMapping("/{id}")
    public Usuario buscaUsuario(@PathVariable  Long id){
       return usuarioService.buscarId(id);
   }
     @Operation(summary = "Excluir usuario por ID", description = "Essa função tem como objetivo excluir o usuario por ID")
     @DeleteMapping("/{id}")
    public void deletaUsuario(@PathVariable Long id) {
         usuarioService.delete(id);
    }

}
