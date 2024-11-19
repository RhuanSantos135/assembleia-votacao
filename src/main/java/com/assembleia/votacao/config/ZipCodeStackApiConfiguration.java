package com.assembleia.votacao.config;


import com.assembleia.votacao.service.ZipCodeStackAPI;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZipCodeStackApiConfiguration {

    @Value("${api.zipcodestack.url}")
    private String url;

    @Bean
    public ZipCodeStackAPI zipCodeStackAPI(){
        return Feign.builder().
                decoder(new JacksonDecoder()).
                encoder(new JacksonEncoder()).
                target(ZipCodeStackAPI.class,url);
    }
}
