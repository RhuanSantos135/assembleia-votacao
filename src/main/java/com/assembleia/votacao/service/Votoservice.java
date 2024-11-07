package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.Voto;
import com.assembleia.votacao.repository.PautaRepository;
import com.assembleia.votacao.repository.UsuarioRepository;
import com.assembleia.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class Votoservice {

    @Autowired
    private PautaRepository pautaRepository;

    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Voto inserirVoto(Voto voto){
         var data = LocalDateTime.now();
         var response = pautaRepository.findById((long) voto.getIdPauta());

         if(pautaRepository.existsById(voto.getIdPauta()) && usuarioRepository.existsById(voto.getIdAssociado())){
             var responseVoto = votoRepository.findByIdAssociadoAndIdPauta(voto.getIdAssociado(),voto.getIdPauta());
             if (responseVoto == null ){
                 if(data.isBefore(response.get().getPrazoPauta())){
                     return votoRepository.save(voto);
                 }
                 throw new RuntimeException("A votação nesta pauta está encerrada devido ao prazo expirado.");
             }
             throw new RuntimeException("Usuário já registrou seu voto.");
         }
        throw new RuntimeException("Usuário ou pauta inexistente.");
    }
}
