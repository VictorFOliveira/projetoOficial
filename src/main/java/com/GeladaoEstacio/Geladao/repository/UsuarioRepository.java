package com.GeladaoEstacio.Geladao.repository;

import com.GeladaoEstacio.Geladao.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNomeUsuarioAndEmail(String nomeUsuario, String email);
}
