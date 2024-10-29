package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PautaService {
    @Autowired
    private PautaRepository repository;

    public ResponseEntity create(Pauta obj){
        repository.save(obj);
        return null;
    }

    public  List<Pauta> getAll(){
        return repository.findAll();
    }

}
