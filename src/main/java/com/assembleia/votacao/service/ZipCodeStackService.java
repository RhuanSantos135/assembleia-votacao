package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.ZipCodeStackLocationResults;
import com.assembleia.votacao.domain.ZipCodeStackResponse;
import com.assembleia.votacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ZipCodeStackService {

    private ZipCodeStackAPI zipCodeStackAPI;
    private UsuarioRepository usuarioRepository;

    public ZipCodeStackService(ZipCodeStackAPI zipCodeStackAPI) {
        this.zipCodeStackAPI = zipCodeStackAPI;
    }

    @Value("${api.zipcodestack.key}")
    private String apiKey;

    public ZipCodeStackResponse getLocation(String code , String country){
        return zipCodeStackAPI.getLocation(code, country, apiKey);
    }
}
