package com.assembleia.votacao.service;

import com.assembleia.votacao.DTO.InPautaDTO;
import com.assembleia.votacao.DTO.OutPautaDTO;
import com.assembleia.votacao.domain.Pauta;
import com.assembleia.votacao.exceptions.ObjectNotFoundException;
import com.assembleia.votacao.mapper.MapperPauta;
import com.assembleia.votacao.repository.PautaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class PautaServiceTest {

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private MapperPauta mapperPauta;

    @InjectMocks
    private PautaService pautaService;

    private Pauta pauta;
    private InPautaDTO inPautaDTO;
    private OutPautaDTO outPautaDTO;
    private Pauta pautaExistente;


    private final LocalDateTime dataFinal = LocalDateTime.of(2024, 12, 17, 10, 0);
    @BeforeEach
    void setUp(){



        pauta = new Pauta();
        pauta.setDescricao("Pauta teste");
        pauta.setId(1L);
        pauta.setPrazoPauta(dataFinal);

        pautaExistente = new Pauta();
        pautaExistente.setId(1L);
        pautaExistente.setDescricao("Pauta Existente");

        inPautaDTO = new InPautaDTO();
        inPautaDTO.setDescricao("Pauta teste");
        inPautaDTO.setPrazoPauta(dataFinal);
        

        outPautaDTO = new OutPautaDTO();
        outPautaDTO.setId(1L);
        outPautaDTO.setDescricao("Pauta teste");
        outPautaDTO.setPrazoPauta(dataFinal);

    }


    @Test
    public void deveRetornaPautaExistente(){

        given(pautaRepository.findById(pauta.getId())).willReturn(Optional.ofNullable(pauta));
        given(mapperPauta.converteParaSaidaPauta(pauta)).willReturn(outPautaDTO);

        var result = pautaService.buscarPauta(pauta.getId());

        assertNotNull(result);
        assertEquals(1L, result.getId());

    }

    @Test
    public void deveRetornaErroPautaNaoEncontrada(){

        given(pautaRepository.findById(any())).willReturn(Optional.empty());

        ObjectNotFoundException exception = assertThrows(ObjectNotFoundException.class, () -> {
            pautaService.buscarPauta(999L);
        } );

        assertEquals("Nenhuma pauta cadastrada no sistema. Verifique e tente novamente.", exception.getMessage());

    }



    @Test
    public void deveCriarPauta(){

        given(pautaRepository.save(any(Pauta.class))).willReturn(pauta);
        given(mapperPauta.converteParaPauta(inPautaDTO)).willReturn(pauta);
        given(mapperPauta.converteParaSaidaPauta(pauta)).willReturn(outPautaDTO);

        var result = pautaService.criaPauta(inPautaDTO);

        assertNotNull(result);


    }


    @Test
    public void deveDeletarPautaPorId(){

        given(pautaRepository.findById(pauta.getId())).willReturn(Optional.of(pauta));
        pautaService.deletaPauta(pauta.getId());
        verify(pautaRepository).deleteById(pauta.getId());
    }


    @Test
    public  void deveCriarPautaSemDataExpiracao() {

        given(mapperPauta.converteParaPauta(inPautaDTO)).willReturn(pautaExistente);
        given(pautaRepository.findById(pautaExistente.getId())).willReturn(Optional.of(pautaExistente));
        given(pautaRepository.save(pautaExistente)).willReturn(pautaExistente);
        given(mapperPauta.converteParaSaidaPauta(pautaExistente)).willReturn(outPautaDTO);


        var result = pautaService.inserirSessao(inPautaDTO);

        assertNotNull(result);
        assertEquals(outPautaDTO, result);
        verify(pautaRepository).findById(1L);
        verify(pautaRepository).save(pautaExistente);
    }

    @Test
    public void deveRetornarErroQuandoPrazoJaDefinido(){

        pautaExistente.setPrazoPauta(LocalDateTime.now().plusMinutes(10));
        given(mapperPauta.converteParaPauta(inPautaDTO)).willReturn(pauta);
        given(pautaRepository.findById(pautaExistente.getId())).willReturn(Optional.of(pautaExistente));

        var exception =  assertThrows(IllegalStateException.class, () ->
                pautaService.inserirSessao(inPautaDTO));
        System.out.println(exception.getMessage());
        assertEquals("Votação em andamento. Não é possível alterar o prazo." , exception.getMessage());

    }

}