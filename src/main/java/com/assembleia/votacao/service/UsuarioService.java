package com.assembleia.votacao.service;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.assembleia.votacao.DTO.InUserDTO;
import com.assembleia.votacao.DTO.OutUserDTO;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.domain.ZipCodeStackLocalAddress;
import com.assembleia.votacao.domain.ZipCodeStackResponse;
import com.assembleia.votacao.exceptions.BadRequestException;
import com.assembleia.votacao.exceptions.ObjectNotFoundException;
import com.assembleia.votacao.mapper.MapperUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.stereotype.Service;
import com.assembleia.votacao.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor;
    private UsuarioRepository repository;


    private ZipCodeStackService zipCodeStackService;


    private final   MapperUser mapperUser;

    public UsuarioService(MapperUser mapperUser, UsuarioRepository repository, ZipCodeStackService zipCodeStackService, OpenEntityManagerInViewInterceptor openEntityManagerInViewInterceptor) {
        this.mapperUser = mapperUser;
        this.repository = repository;
        this.zipCodeStackService = zipCodeStackService;
        this.openEntityManagerInViewInterceptor = openEntityManagerInViewInterceptor;
    }

    public OutUserDTO buscarId(Long id) {
        var usuario = repository.findById(id);
        if (usuario.isEmpty()) {
            usuario.orElseThrow(() -> new ObjectNotFoundException("O usuário especificado não existe."));
        }
        return mapperUser.converteParaSaidaUsuario(usuario.get());
    }
    public OutUserDTO create(InUserDTO inUserDTO) {
        Usuario usuario = mapperUser.converteParaUsuaruio(inUserDTO);
        if (usuario.getNome().isEmpty() || repository.findByEmail(usuario.getEmail()) != null) {
            throw new BadRequestException("O campo de nome é obrigatório e o usuário já está cadastrado.");
        }
        var senhaHash = BCrypt.withDefaults().hashToString(12, usuario.getSenha().toCharArray());
        usuario.setSenha(senhaHash);

        if (usuario.getPostal_code() != null && !usuario.getPostal_code().isEmpty()) {
            ZipCodeStackResponse response = zipCodeStackService.getLocation(usuario.getPostal_code(), "BR");

            if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
                List<ZipCodeStackLocalAddress> addresses = response.getResults().get(usuario.getPostal_code());

                if (addresses != null && !addresses.isEmpty()) {
                    ZipCodeStackLocalAddress location = addresses.get(0);
                    usuario.setCity_en(location.getCity_en());
                    usuario.setState_en(location.getState_en());
                }
            }
        } else {
            throw new BadRequestException("Postal Code Deve estar preenchido");
        }
        return mapperUser.converteParaSaidaUsuario(usuario);
    }

    public void delete(Long id) {
        var usuario = repository.findById(id);
        if (usuario.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new ObjectNotFoundException("O usuário especificado não existe ou já foi excluído.");
        }
    }


    public List<Usuario> getAll(){
        return repository.findAll();
    }
}
