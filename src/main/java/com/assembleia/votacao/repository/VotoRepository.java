package com.assembleia.votacao.repository;

import com.assembleia.votacao.domain.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {


}
