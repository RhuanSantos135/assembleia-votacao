package com.assembleia.votacao.service;

import com.assembleia.votacao.DTO.InUserDTO;
import com.assembleia.votacao.DTO.OutUserDTO;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.exceptions.BadRequestException;
import com.assembleia.votacao.exceptions.ObjectNotFoundException;
import com.assembleia.votacao.mapper.MapperUser;
import com.assembleia.votacao.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    private Usuario usuario;
    private OutUserDTO outUserDTO;
    private InUserDTO inUserDTO;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setIdAssociado(1L);
        usuario.setNome("Rhuan");
        usuario.setEmail("rhuan@example.com");
        usuario.setSenha("senha123");
        usuario.setPostal_code("90210");


        outUserDTO = new OutUserDTO();
        outUserDTO.setIdAssociado(1L);
        outUserDTO.setNome("Rhuan");
        outUserDTO.setEmail("rhuan@example.com");

        inUserDTO = new InUserDTO();
        inUserDTO.setNome("Rhuan");
        inUserDTO.setEmail("rhuan@example.com");
        inUserDTO.setSenha("senha123");
        inUserDTO.setPostal_code("90210");
    }

    @Test
    public void deveRetornaUsuarioPorId() {
        given(usuarioRepository.findById(usuario.getIdAssociado())).willReturn(Optional.of(usuario));
        given(mapperUser.converteParaSaidaUsuario(usuario)).willReturn(outUserDTO);

        var result = usuarioService.buscarId(usuario.getIdAssociado());

        assertNotNull(result);
        assertEquals(1L, result.getIdAssociado());
    }

    @Test
    public void deveRetornaErroUsuarioNaoEncontrado() {
        given(usuarioRepository.findById(any())).willReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            usuarioService.buscarId(999L);
        });

        assertEquals("O usuário especificado não existe.", exception.getMessage());
    }

    @Test
    public void deveCriarUsuario() {

        given(mapperUser.converteParaUsuaruio(inUserDTO)).willReturn(usuario);
        given(mapperUser.converteParaSaidaUsuario(usuario)).willReturn(outUserDTO);


        var resultado = usuarioService.create(inUserDTO);

        assertNotNull(resultado);
        assertEquals("Rhuan", resultado.getNome());
        assertEquals("rhuan@example.com", resultado.getEmail());
    }

    @Test
    public void deveRetornarErroNomeNaoEncontrato() {
        var inuser = mock(InUserDTO.class);
        var usuarioburro = mock(Usuario.class);

        given(usuarioburro.getNome()).willReturn("");
        given(mapperUser.converteParaUsuaruio(inuser)).willReturn(usuarioburro);

        var exception = assertThrows(BadRequestException.class, () -> {
            usuarioService.create(inuser);

        });
        assertEquals("O campo de nome é obrigatório e o usuário já está cadastrado.", exception.getMessage());

    }

    @Test
    public void deveRetornaErroEmailDiferenteDeNull() {

        given(mapperUser.converteParaUsuaruio(inUserDTO)).willReturn(usuario);
        given(usuarioRepository.findByEmail(usuario.getEmail())).willReturn(usuario);
        var exception = assertThrows(BadRequestException.class, () -> {
            usuarioService.create(inUserDTO);

        });
        assertEquals("O campo de nome é obrigatório e o usuário já está cadastrado.", exception.getMessage());

    }

    @Test
    public void deveRetornaErroPostalCodeNaoPreenchido() {

        given(mapperUser.converteParaUsuaruio(inUserDTO)).willReturn(usuario);
        usuario.setPostal_code(null);

        var execption = assertThrows(BadRequestException.class, () -> {
            usuarioService.create(inUserDTO);
        });
        assertEquals("Postal Code Deve estar preenchido", execption.getMessage());
    }


    @Test
    public void deveExcluirUsuarioPorId() {
        given(usuarioRepository.findById(usuario.getIdAssociado())).willReturn(Optional.of(usuario));
        usuarioService.delete(usuario.getIdAssociado());
        verify(usuarioRepository).deleteById(usuario.getIdAssociado());
    }

    @Test
    public void deveRetornaErroUsuarioNaoEncontradoAoExcluir(){
        var user = mock(Usuario.class);

        var execption = assertThrows(ObjectNotFoundException.class , () -> {
            usuarioService.delete(user.getIdAssociado());
        });
        assertEquals("O usuário especificado não existe ou já foi excluído." , execption.getMessage());

    }


    @Test
    public void deveRetornaTodosUsuario(){

        given(usuarioRepository.findAll()).willReturn(List.of(usuario));

        var result = usuarioService.getAll();

        assertEquals(result.get(0).getIdAssociado(), 1L);
        assertEquals(result.get(0).getNome(), "Rhuan");
        assertEquals(result.get(0).getEmail(), "rhuan@example.com");
        assertEquals(result.get(0).getPostal_code(), "90210");
        assertEquals(result.get(0).getSenha(), "senha123");



    }


}
