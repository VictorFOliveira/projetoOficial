package com.GeladaoEstacio.Geladao.web.returnDtos;

import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
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
import java.util.Date;

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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataCriacao;
<<<<<<< HEAD
=======
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDateTime dataModificacao;
    private String criadoPor;
    private String modificadoPor;
>>>>>>> 4c20e66 (alterações finais)
    @Enumerated(EnumType.ORDINAL)
    private Usuario.StatusUsuario statusUsuario;
}
