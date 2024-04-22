package com.GeladaoEstacio.Geladao.web.controller;


import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.service.UsuarioService;
import com.GeladaoEstacio.Geladao.web.returnDtos.ReturnUsuarioDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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


    @GetMapping("/operacoes_usuarios")
    public ModelAndView operacoesProdutosPage() {
        ModelAndView modelAndView = new ModelAndView("operacoes_usuarios/operacoes_usuarios");
        Resource resource = resourceLoader.getResource("classpath:/templates/operacoes_usuarios/operacoes_usuarios.html");
        if (!resource.exists()) {
            modelAndView = new ModelAndView("error/404");
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
        }
        return modelAndView;
    }

    @PostMapping("/criar")
    public ResponseEntity<ReturnUsuarioDTO> createUser(@Valid @RequestBody Usuario usuario) {
        usuarioService.createUser(usuario);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnUsuarioDTO);
    }

    @GetMapping("/buscarUser/{id}")
    public ResponseEntity<ReturnUsuarioDTO> buscarUsuarioId(@PathVariable Long id) {
        Usuario usuario = usuarioService.buscarUser(id);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);

    }

    @GetMapping("/buscarTodosUsers")
    public ResponseEntity<List<ReturnUsuarioDTO>> recuperarTodosUsuarios() {
        List<Usuario> usuarios = usuarioService.buscarUsuarios();
        List<ReturnUsuarioDTO> returnUsuarioDTOS = usuarios.stream()
                .sorted(Comparator.comparing(Usuario::getId)) // Ordena pelo nome de usuário
                .map(usuario -> modelMapper.map(usuario, ReturnUsuarioDTO.class))
                .toList();
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTOS);
    }

    @PatchMapping("/alterarUser/{id}")
    public ResponseEntity<ReturnUsuarioDTO> alterarUsuario(@PathVariable Long id, @RequestBody String nome, @RequestBody  String email){
        Usuario usuario = usuarioService.atualizarUsuario(id, nome, email);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);
    }

    @PatchMapping("/AlterarAcessoADM/{id}")
    public ResponseEntity<ReturnUsuarioDTO> alterarUsarioToADM(@PathVariable Long id) {
        Usuario usuario = usuarioService.alterarAcessoUsuarioToADM(id);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);

    }

    @PatchMapping("/AlterarAcessoVend/{id}")
    public ResponseEntity<ReturnUsuarioDTO> alterarUsarioToAVend(@PathVariable Long id) {
        Usuario usuario = usuarioService.alterarAcessoUsuarioToVEND(id);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);
    }

    @PatchMapping("/ativarUser/{id}")
    public ResponseEntity<ReturnUsuarioDTO> ativarUser(@PathVariable Long id) {
        Usuario usuario = usuarioService.ativarUser(id);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);
    }

    @PatchMapping("/desativarUser/{id}")
    public ResponseEntity<ReturnUsuarioDTO> desativarUser(@PathVariable Long id) {
        Usuario usuario = usuarioService.desativarUser(id);
        ReturnUsuarioDTO returnUsuarioDTO = modelMapper.map(usuario, ReturnUsuarioDTO.class);
        return ResponseEntity.status(HttpStatus.OK).body(returnUsuarioDTO);
    }

}
