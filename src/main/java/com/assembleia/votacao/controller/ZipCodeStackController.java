package com.assembleia.votacao.controller;

import com.assembleia.votacao.domain.ZipCodeStackLocationResults;
import com.assembleia.votacao.domain.ZipCodeStackResponse;
import com.assembleia.votacao.service.ZipCodeStackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/zip-code")

public class ZipCodeStackController {
    @Autowired
    private ZipCodeStackService zipCodeStackService;

    @GetMapping
    public ZipCodeStackResponse getLocation(@RequestParam(value = "code") String code, @RequestParam(value = "country") String country)
    {
        return zipCodeStackService.getLocation(code, country);
    }

}
