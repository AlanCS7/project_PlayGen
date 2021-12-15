package com.minhaLojaDeGames.PlayGen.controller;

import com.minhaLojaDeGames.PlayGen.model.UsuarioModel;
import com.minhaLojaDeGames.PlayGen.model.dtos.UsuarioCredenciaisDTO;
import com.minhaLojaDeGames.PlayGen.model.dtos.UsuarioLoginDTO;
import com.minhaLojaDeGames.PlayGen.model.dtos.UsuarioRegistroDTO;
import com.minhaLojaDeGames.PlayGen.service.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/playGen/usuario")
@CrossOrigin(allowedHeaders = "*", origins = "*")
public class UsuarioController {
    @Autowired private UsuarioServices services;

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioModel> cadastrar (@Valid @RequestBody UsuarioRegistroDTO novoUsuario){
        return services.cadastrarUsuario(novoUsuario);
    }

    @PutMapping("/login")
    public ResponseEntity<UsuarioCredenciaisDTO> logar (@Valid @RequestBody UsuarioLoginDTO usuario){
        return services.logarUsuario(usuario);
    }
}
