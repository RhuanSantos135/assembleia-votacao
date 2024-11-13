package com.assembleia.votacao.service;


import at.favre.lib.crypto.bcrypt.BCrypt;
import com.assembleia.votacao.domain.Usuario;
import com.assembleia.votacao.exceptions.BadRequestException;
import com.assembleia.votacao.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assembleia.votacao.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;


    public Usuario buscarId(Long id) {
        var usuario = repository.findById(id);
        if (usuario.isEmpty()) {
            usuario.orElseThrow(() -> new ObjectNotFoundException("O usuário especificado não existe."));
        }
        return usuario.get();
    }

    public Usuario create(Usuario usuario){
        var response = repository.findByEmail(usuario.getEmail());
        var senhahash = BCrypt.withDefaults().hashToString(12,usuario.getSenha().toCharArray());
        usuario.setSenha(senhahash);
        if(usuario.getNome().isEmpty() && response != null ){
            throw new BadRequestException("O campo de nome é obrigatório e não pode estar vazio. || O usuário informado já está cadastrado no sistema.");
        } else{
            return  repository.save(usuario);
        }
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
