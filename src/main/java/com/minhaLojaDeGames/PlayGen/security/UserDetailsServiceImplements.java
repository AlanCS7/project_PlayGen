package com.minhaLojaDeGames.PlayGen.security;

import com.minhaLojaDeGames.PlayGen.model.UsuarioModel;
import com.minhaLojaDeGames.PlayGen.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;


@Service
public class UserDetailsServiceImplements implements UserDetailsService {

    private @Autowired
    UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsuarioModel> optional = repository.findByEmail(username);

        if (optional.isPresent()){
            return new UserDetailsImplement(optional.get());
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, username + "não foi encontrado");
        }

    }
}
