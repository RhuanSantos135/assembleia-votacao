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

    public boolean emailJaCadastrado(String email){
        return repository.findbyEmail(email).isPresent();
    }

    public Usuario create(Usuario usuario) throws IllegalAccessException {
        if (emailJaCadastrado(usuario.getEmail())){
            throw new IllegalAccessException("E-mail já cadastrato");
        }
        return repository.save(usuario);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public List<Usuario> getAll(){
        return repository.findAll();
    }
}
