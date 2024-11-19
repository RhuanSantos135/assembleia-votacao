package com.assembleia.votacao.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ZipCodeStackLocalAddress {

    private String postal_code;
    private String country_code;
    private int latitude;
    private int longitude;
    private String city;
    private String state;
    private String city_en;
    private String state_en;
    private String state_code;

}