package com.GeladaoEstacio.Geladao.service;

import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.repository.UsuarioRepository;
import com.GeladaoEstacio.Geladao.web.exception.UsuarioException;
import jakarta.transaction.Transactional.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    @Transactional
    public Usuario createUser(Usuario usuario) {
        if (usuario.getNomeUsuario().isEmpty()) {
            throw new UsuarioException(UsuarioException.USUARIO_SEM_NOME);
        }
        if (usuario.getEmail().isEmpty()) {
            throw new UsuarioException(UsuarioException.USUARIO_SEM_EMAIL);
        }
        usuario.setDataCriacao(LocalDateTime.now());
        usuario.setAcesso(Usuario.Acesso.ACESSO_VENDEDOR);
        usuario.setStatusUsuario(Usuario.StatusUsuario.getDefaultStatus());
        usuarioRepository.save(usuario);
        return usuario;
    }

    @Transactional(readOnly = true)
    public Usuario buscarUser(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO));
    }

    @Transactional(readOnly = true)
    public List<Usuario> buscarUsuarios() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario atualizarUsuario(Long id, String nome, String email) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO));

        if (StringUtils.isEmpty(nome)) {
            throw new UsuarioException(UsuarioException.USUARIO_SEM_NOME);
        }
        if (StringUtils.isEmpty(email)) {
            throw new UsuarioException(UsuarioException.USUARIO_SEM_EMAIL);
        }
        usuario.setNomeUsuario(nome);
        usuario.setEmail(email);
        usuario.setDataModificacao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario alterarAcessoUsuarioToADM(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO));
        if (usuario.getAcesso() == Usuario.Acesso.getDefaultAcesso()) {
            throw new UsuarioException(UsuarioException.USUARIO_ADM);
        }
        usuario.setAcesso(Usuario.Acesso.ACESSO_ADMIN);
        usuario.setDataModificacao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }
    @Transactional
    public Usuario alterarAcessoUsuarioToVEND(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO));
        if (usuario.getAcesso() == Usuario.Acesso.getDefaultAcesso()) {
            throw new UsuarioException(UsuarioException.USUARIO_VENDEDOR);
        }
        usuario.setAcesso(Usuario.Acesso.ACESSO_VENDEDOR);
        usuario.setDataModificacao(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario desativarUser(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO));
        if (usuario.getStatusUsuario() == Usuario.StatusUsuario.USUARIO_INATIVO) {
            throw new UsuarioException(UsuarioException.USUARIO_INATIVO);
        }
        usuario.setStatusUsuario(Usuario.StatusUsuario.USUARIO_INATIVO);
        usuario.setDataModificacao(LocalDateTime.now());
        return usuario;
    }

    @Transactional
    public Usuario ativarUser(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO));
        if(usuario.getStatusUsuario() == Usuario.StatusUsuario.USUARIO_ATIVO){
            throw new RuntimeException("Usuário está ativo");
        }
        usuario.setStatusUsuario(Usuario.StatusUsuario.USUARIO_ATIVO);
        usuario.setDataModificacao(LocalDateTime.now());
        return usuario;
    }
}


