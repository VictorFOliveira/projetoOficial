package com.GeladaoEstacio.Geladao.web.returnDtos;

import com.GeladaoEstacio.Geladao.entities.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnUsuarioDTO {

    private Long id;
    private String nomeUsuario;
    private String email;
    @Enumerated(EnumType.ORDINAL)
    private Usuario.Acesso acesso;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataModificacao;
    private String criadoPor;
    private String modificadoPor;
    @Enumerated(EnumType.ORDINAL)
    private Usuario.StatusUsuario statusUsuario;
}
