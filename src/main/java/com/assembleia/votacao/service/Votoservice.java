package com.assembleia.votacao.service;


import com.assembleia.votacao.DTO.InVotoDTO;
import com.assembleia.votacao.DTO.OutVotoDTO;
import com.assembleia.votacao.DTO.ResultadoDTO;
import com.assembleia.votacao.domain.Voto;
import com.assembleia.votacao.mapper.MapperVoto;
import com.assembleia.votacao.repository.PautaRepository;
import com.assembleia.votacao.repository.UsuarioRepository;
import com.assembleia.votacao.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class Votoservice {

    @Autowired
    private PautaRepository pautaRepository;
    @Autowired
    private VotoRepository votoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MapperVoto mapperVoto;

    public OutVotoDTO inserirVoto(InVotoDTO inVotoDTO){
        Voto voto =  mapperVoto.converteParaVoto(inVotoDTO);
         var data = LocalDateTime.now();
         var response = pautaRepository.findById((long) voto.getIdPauta());

         if(pautaRepository.existsById(voto.getIdPauta()) && usuarioRepository.existsById(voto.getIdAssociado())){
             var responseVoto = votoRepository.findByIdAssociadoAndIdPauta(voto.getIdAssociado(),voto.getIdPauta());
             if (responseVoto == null ){
                 if(data.isBefore(response.get().getPrazoPauta())){
                     return mapperVoto.converteParaSaidaVoto(votoRepository.save(voto));
                 }
                 throw new RuntimeException("A votação nesta pauta está encerrada devido ao prazo expirado.");
             }
             throw new RuntimeException("Usuário já registrou seu voto.");
         }
        throw new RuntimeException("Usuário ou pauta inexistente.");
    }

    public ResultadoDTO calcularResultado(Long idPauta ) {
        var dataLocal = LocalDateTime.now();
        var pauta = pautaRepository.findById(idPauta);

        var votos = votoRepository.findByIdPauta(idPauta);

        if(dataLocal.isBefore(pauta.get().getPrazoPauta())){
            throw new RuntimeException("Votação em andamento!");
        }
        int votosSim = 0;
        int votosNao = 0 ;
        String resultado = "";
        for (int contador = 0 ; contador < votos.size() ; contador++){
            if (votos.get(contador).getVotosSimNao()){
                votosSim++;
            } else {
                votosNao++;
            }
        }
        if(votosSim > votosNao){
            resultado = "Pauta aprovada!";
        }
        else {
        resultado =  "Pauta reprovada!";
        }

        return new ResultadoDTO( idPauta, pauta.get().getDescricao(), votosSim, votosNao, resultado);

    }









}
