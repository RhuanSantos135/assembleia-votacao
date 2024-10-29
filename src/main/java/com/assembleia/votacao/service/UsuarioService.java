package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.assembleia.votacao.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;


    public Usuario buscarId(Long id){
        Optional<Usuario> usuario = repository.findById(id);
        if(usuario.isEmpty()){
            throw new RuntimeException("Usuario inesistente");
        } else {
            return usuario.get();
        }

    }


    public Usuario create(Usuario usuario){
        return repository.save(usuario);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Usuario> getAll(){
        return repository.findAll();
    }
}
