package com.GeladaoEstacio.Geladao.web.controller;


import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.service.UsuarioService;
import com.GeladaoEstacio.Geladao.web.dtos.UsuarioDTO;
import com.GeladaoEstacio.Geladao.web.returnDtos.ReturnUsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.hibernate.query.NativeQuery;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Usuarios", description = "Contém todas as operações relativas aos recursos para cadastro, edição e leitura de um usuario.")
@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private ResourceLoader resourceLoader;


    @PostMapping("/criar")
    public ResponseEntity<ReturnUsuarioDTO> createUser(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioService.createUser(usuario);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnUsuarioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReturnUsuarioDTO> getUser(@PathVariable Long id) {
        usuarioService.findById(id);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuarioService.findById(id), ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<ReturnUsuarioDTO>> findAll() {
        List<Usuario> usuarios = usuarioService.findAll();
        List<ReturnUsuarioDTO> returnUsuariosDTO = usuarios.stream()
                .map(usuario -> modelMapper.map(usuario, ReturnUsuarioDTO.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(returnUsuariosDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ReturnUsuarioDTO> deactivateUser(@PathVariable Long id) {
        Usuario usuario = usuarioService.desativarAcesso(id);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);
    }

    @PutMapping("/alterarAcesso/{id}")
    public ResponseEntity<ReturnUsuarioDTO> updateAcess(@PathVariable Long id){
        Usuario usuario = usuarioService.alteraAcessoAdm(id);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);
    }
}
