package com.assembleia.votacao.controller;


import com.assembleia.votacao.DTO.AccessDTO;
import com.assembleia.votacao.DTO.AuthenticationDTO;
import com.assembleia.votacao.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping(value = "/login")
    public AccessDTO login(@RequestBody AuthenticationDTO authenticationDTO){
        return authService.login(authenticationDTO);
    }


}