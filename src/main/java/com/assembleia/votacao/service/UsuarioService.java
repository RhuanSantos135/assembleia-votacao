package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.assembleia.votacao.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;


    public ResponseEntity create(Usuario obj){
        repository.save(obj);
        return null;
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public Usuario getId(Long id){
        Optional<Usuario> obj = repository.findById(id);
        return  obj.get();
    }
    public List<Usuario> getAll(){
        return repository.findAll();
    }
}
