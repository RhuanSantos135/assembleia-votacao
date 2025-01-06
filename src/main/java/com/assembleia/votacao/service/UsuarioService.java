package com.assembleia.votacao.service;


import com.assembleia.votacao.DTO.InUserDTO;
import com.assembleia.votacao.DTO.OutUserDTO;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.exceptions.BadRequestException;
import com.assembleia.votacao.exceptions.ObjectNotFoundException;
import com.assembleia.votacao.mapper.MapperUser;
import com.assembleia.votacao.validation.UsuarioValidation;
import org.springframework.stereotype.Service;
import com.assembleia.votacao.repository.UsuarioRepository;

import java.util.List;

import static com.assembleia.votacao.validation.UsuarioValidation.geraSenhaCriptografada;
import static com.assembleia.votacao.validation.UsuarioValidation.validaCampos;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private UsuarioRepository repository;


    private UsuarioValidation usuarioValidation;

    private final   MapperUser mapperUser;

    public UsuarioService(MapperUser mapperUser, UsuarioRepository repository, UsuarioValidation usuarioValidation, UsuarioRepository usuarioRepository) {
        this.mapperUser = mapperUser;
        this.repository = repository;
        this.usuarioValidation = usuarioValidation;
        this.usuarioRepository = usuarioRepository;
    }

    public OutUserDTO buscarUsuarioId(Long id) {
        var usuario = repository.findById(id);
        if (usuario.isEmpty()) {
            throw new ObjectNotFoundException("O usuário especificado não existe.");
        }
        return mapperUser.converteParaSaidaUsuario(usuario.get());
    }

    public OutUserDTO create(InUserDTO inUserDTO) {

        var usuario = mapperUser.converteParaUsuaruio(inUserDTO);

        validaCampos(usuario);

        usuario.setSenha(geraSenhaCriptografada(usuario.getSenha()));

        if (repository.findByEmail(usuario.getEmail()) != null) {
            throw new BadRequestException("O email já está em uso.");
        }

        return mapperUser.converteParaSaidaUsuario(usuario);
    }


    public void deletaPorIdUser(Long id) {
        repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("O usuário especificado não existe ou já foi excluído."));
        repository.deleteById(id);
    }


    public List<Usuario> getAll() {
        List<Usuario> usuarios = repository.findAll();
        if (usuarios.isEmpty()) {
            throw new ObjectNotFoundException("Nenhum usuário encontrado.");
        }
        return usuarios;
    }

}
