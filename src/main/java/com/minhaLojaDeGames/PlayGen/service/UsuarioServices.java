package com.minhaLojaDeGames.PlayGen.service;

import com.minhaLojaDeGames.PlayGen.model.UsuarioModel;
import com.minhaLojaDeGames.PlayGen.model.dtos.UsuarioCredenciaisDTO;
import com.minhaLojaDeGames.PlayGen.model.dtos.UsuarioLoginDTO;
import com.minhaLojaDeGames.PlayGen.model.dtos.UsuarioRegistroDTO;
import com.minhaLojaDeGames.PlayGen.repository.UsuarioRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.Charset;
import java.util.Optional;

@Service
public class UsuarioServices {

    private @Autowired UsuarioRepository repository;
    private UsuarioModel usuario;
    private UsuarioCredenciaisDTO credencias;

    private static String encriptarSenha(String senha){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return  encoder.encode(senha);
    }

    private static String geradorToken(String email, String senha){
        String estrutura = email + ":" + senha;
        byte[] token = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
        return new String(token);
    }

    private static String geradorTokenBasic(String email, String senha) {
        String estrutura = email + ":" + senha;
        byte[] token = Base64.encodeBase64(estrutura.getBytes(Charset.forName("US-ASCII")));
        return "Basic " + new String(token);
    }

    public ResponseEntity<UsuarioModel> cadastrarUsuario (UsuarioRegistroDTO novoUsuario){
        Optional<UsuarioModel> optional = repository.findByEmail(novoUsuario.getEmail());
        if(optional.isPresent()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado!");
        }else{
            usuario = new UsuarioModel();
            usuario.setNome(novoUsuario.getNome());
            usuario.setEmail(novoUsuario.getEmail());
            usuario.setSenha(encriptarSenha(novoUsuario.getSenha()));
            usuario.setToken(geradorToken(novoUsuario.getEmail(), novoUsuario.getSenha()));
            return ResponseEntity.status(201).body(repository.save(usuario));
        }
    }
    public ResponseEntity<UsuarioCredenciaisDTO> logarUsuario (UsuarioLoginDTO usuario){
         return repository.findByEmail(usuario.getEmail()).map(resp -> {
             BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (encoder.matches(usuario.getSenha(),resp.getSenha())){
                    credencias = new UsuarioCredenciaisDTO();
                    credencias.setIdUsuario(resp.getIdUsuario());
                    credencias.setEmail(resp.getEmail());
                    credencias.setToken(resp.getToken());
                    credencias.setTokenBasic(geradorTokenBasic(usuario.getEmail(), usuario.getSenha()));

                    return  ResponseEntity.status(200).body(credencias);
                } else {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Senha incorreta");
                }

          }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email não encontrado"));

    }
}
