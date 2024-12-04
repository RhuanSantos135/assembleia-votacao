package com.assembleia.votacao.service;

import com.assembleia.votacao.DTO.OutUserDTO;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.mapper.MapperUser;
import com.assembleia.votacao.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ZipCodeStackService zipCodeStackService;

    @Mock
    private MapperUser mapperUser;

    @InjectMocks
    private UsuarioService usuarioService;



    @Test
    public void deveRetornaUsuarioPorId(){

        var usuario = new Usuario();
        var outusuario = new OutUserDTO();

        usuario.setIdAssociado(1L);
        outusuario.setIdAssociado(1L);

        when(mapperUser.converteParaSaidaUsuario(usuario)).thenReturn(outusuario);
        when(usuarioRepository.findById(usuario.getIdAssociado())).thenReturn(Optional.of(usuario));
        var result = usuarioService.buscarId(usuario.getIdAssociado());

        assertEquals(1L, result.getIdAssociado());


    }

}