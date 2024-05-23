package com.GeladaoEstacio.Geladao.web.returnDtos;


import com.GeladaoEstacio.Geladao.entities.ItensVendidos;
import com.GeladaoEstacio.Geladao.entities.Usuario;
import com.GeladaoEstacio.Geladao.web.dtos.ItensVendidosDTO;
import com.GeladaoEstacio.Geladao.web.dtos.UsuarioDTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnVendaDTO {


    private Long id;
    private UsuarioDTO usuario;
<<<<<<< HEAD
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
=======
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy : HH:mm:ss")
>>>>>>> 4c20e66 (alterações finais)
    private LocalDateTime dataVenda;
    private List<ReturnItensVendidosDTO> itensVendidos = new ArrayList<>();
    private Double valorTotal;
}
