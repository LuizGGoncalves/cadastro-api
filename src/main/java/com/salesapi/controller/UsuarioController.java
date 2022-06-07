package com.salesapi.controller;

import com.salesapi.model.UsuarioDto;
import com.salesapi.model.UsuarioDtoRegister;
import com.salesapi.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(@RequestBody UsuarioDtoRegister usuario ) {
        return ResponseEntity.status(201).body(usuarioService.createUser(usuario,"cadastro"));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDto>> getUsuario() {
        return ResponseEntity.status(200).body(usuarioService.getAllUsuario());
    }
}
