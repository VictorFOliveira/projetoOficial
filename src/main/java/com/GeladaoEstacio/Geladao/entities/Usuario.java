package com.GeladaoEstacio.Geladao.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Usuario implements Serializable {


    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Long id;
    @Column(name = "nome_usuario", nullable = false)
    @NotBlank
    @NotNull
    private String nomeUsuario;
    @Email
    @NotBlank
    @Column(name = "email_usuario", nullable = false)
    private String email;
    @Column(name = "senha_usuario")
    private String senha;
    @Column(name = "acesso_usuario")
    @Enumerated(EnumType.ORDINAL)
    private Acesso acesso;


    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;
    @Column(name = "data_modificacao")
    private LocalDateTime dataModificacao;
    @Column(name = "criado_por")
    private String criadoPor;
    @Column(name = "modificado_por")
    private String modificadoPor;
    @Column(name = "status_usuario")
    @Enumerated(EnumType.ORDINAL)
    private StatusUsuario statusUsuario;

    public enum Acesso {
        ACESSO_VENDEDOR(0),
        ACESSO_ADMIN(1);

        private final int numero;

        Acesso(int numero) {
            this.numero = numero;
        }

        public int getNumero() {
            return numero;
        }

        public static Acesso getDefaultAcesso() {
            return ACESSO_VENDEDOR;
        }
    }

    public enum StatusUsuario {
        USUARIO_ATIVO(0),
        USUARIO_INATIVO(1);

        private final int numero;

        StatusUsuario(int numero) {
            this.numero = numero;
        }

        public int getNumero() {
            return numero;
        }

        public static StatusUsuario getDefaultStatus() {
            return USUARIO_ATIVO;
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

