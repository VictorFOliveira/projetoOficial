package com.GeladaoEstacio.Geladao.service;

import com.GeladaoEstacio.Geladao.Utils.SenhaUtils;
import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.repository.UsuarioRepository;
import com.GeladaoEstacio.Geladao.web.exception.ProdutoException;
import com.GeladaoEstacio.Geladao.web.exception.UsuarioException;
import jakarta.transaction.Transactional.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
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
        usuario.setDataCriacao(new Date());
<<<<<<< HEAD
        usuario.setSenha(SenhaUtils.gerarSenhaAleatoria());
        usuario.setStatusUsuario(Usuario.StatusUsuario.getDefaultStatus());
        usuario.setAcesso(Usuario.Acesso.getDefaultAcesso());

=======
        usuario.setAcesso(Usuario.Acesso.ACESSO_VENDEDOR);
        usuario.setStatusUsuario(Usuario.StatusUsuario.getDefaultStatus());
        usuario.setSenha(SenhaUtils.gerarSenhaAleatoria());
>>>>>>> 4c20e66 (alterações finais)
        usuarioRepository.save(usuario);

        return usuario;
    }

    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO));
    }

    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Transactional
    public Usuario alteraAcessoAdm(Long id) {
        Usuario usuario = findById(id);
        if (usuario.getAcesso() != Usuario.Acesso.ACESSO_VENDEDOR) {
            usuario.setAcesso(Usuario.Acesso.ACESSO_VENDEDOR);
        }
        return usuario;
    }

    @Transactional
    public Usuario desativarAcesso(Long  id) {
        Usuario usuario = findById(id);
        if(usuario.getStatusUsuario() != Usuario.StatusUsuario.USUARIO_INATIVO){
            usuario.setStatusUsuario(Usuario.StatusUsuario.USUARIO_INATIVO);
        }
        return usuario;
    }

    @Transactional
    public String excluirUsuario(Long id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new UsuarioException(UsuarioException.USUARIO_NAO_ENCONTRADO));
        usuarioRepository.delete(usuario);
        return "$Usuário deletado {usuario}";
    }
}



