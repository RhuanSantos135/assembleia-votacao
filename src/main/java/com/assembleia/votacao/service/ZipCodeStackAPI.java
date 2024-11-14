package com.assembleia.votacao.service;


import com.assembleia.votacao.domain.ZipCodeStackLocationResults;
import com.assembleia.votacao.domain.ZipCodeStackResponse;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.stereotype.Service;

@Service
public interface ZipCodeStackAPI{
    @RequestLine("GET v1/search?codes={codes}&country={country}&apikey={apiKey}")
    @Headers("Content-Type: application/json")
    ZipCodeStackResponse getLocation(@Param("codes") String codes, @Param("country") String country, @Param("apiKey") String apiKey);
}
