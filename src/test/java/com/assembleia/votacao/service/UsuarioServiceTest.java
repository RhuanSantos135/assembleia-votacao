package com.assembleia.votacao.service;

import com.assembleia.votacao.DTO.InUserDTO;
import com.assembleia.votacao.DTO.OutUserDTO;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.exceptions.ObjectNotFoundException;
import com.assembleia.votacao.mapper.MapperUser;
import com.assembleia.votacao.repository.UsuarioRepository;
import org.instancio.Instancio;
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
import static org.mockito.Mockito.*;


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
        given(mapperUser.converteParaSaidaUsuario(usuario)).willReturn(outusuario);
        given(usuarioRepository.findById(usuario.getIdAssociado())).willReturn(Optional.of(usuario));
        var result = usuarioService.buscarId(usuario.getIdAssociado());
        assertEquals(1L, result.getIdAssociado());
    }


    @Test
    public void deveRetornaErroUsuarioNaoEspecificado(){
        Long idInexistente = 999L;
        given(usuarioRepository.findById(idInexistente)).willReturn(Optional.empty());
        assertThrows(ObjectNotFoundException.class, () -> {
            usuarioService.buscarId(idInexistente);
        });
    }


    @Test
    public void deveCriarUmUsuarioSemValidacoes() {
        var inUserDTO = new InUserDTO();
        inUserDTO.setNome("Rhuan");
        inUserDTO.setEmail("rhuan@example.com");
        inUserDTO.setSenha("senha123");

        var usuario = new Usuario();
        usuario.setNome("Rhuan");
        usuario.setEmail("rhuan@example.com");
        usuario.setSenha("senha123");

        var outUserDTO = new OutUserDTO();
        outUserDTO.setNome("Rhuan");
        outUserDTO.setEmail("rhuan@example.com");



        when(mapperUser.converteParaUsuaruio(inUserDTO)).thenReturn(usuario);
        when(mapperUser.converteParaSaidaUsuario(usuario)).thenReturn(outUserDTO);
        OutUserDTO resultado = usuarioService.create(inUserDTO);


        assertNotNull(resultado);
        assertEquals("Rhuan", resultado.getNome());
        assertEquals("rhuan@example.com", resultado.getEmail());
    }
}