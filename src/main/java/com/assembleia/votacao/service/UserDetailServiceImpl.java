package com.assembleia.votacao.service;


import com.assembleia.votacao.mapper.MapperUserDetails;
import com.assembleia.votacao.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MapperUserDetails mapperUserDetails;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var usuario = usuarioRepository.findByEmail(username).get();
        return (UserDetails) mapperUserDetails.converteParaUserDetails(Optional.of(usuario));
    }
}
