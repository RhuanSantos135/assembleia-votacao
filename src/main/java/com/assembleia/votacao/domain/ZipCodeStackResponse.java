package com.assembleia.votacao.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ZipCodeStackResponse {

    private ZipCodeStackPostalCodeQuery query;
    private Map<String , List<ZipCodeStackLocalAddress>> results;

}
