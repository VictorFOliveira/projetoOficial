package com.GeladaoEstacio.Geladao.web.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor

public class VendaDTO {

    private UsuarioDTO usuario;
    private List<ItensVendidosDTO> itensVendidos = new ArrayList<>();
}
