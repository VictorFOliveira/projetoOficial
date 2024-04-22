package com.GeladaoEstacio.Geladao.web.dtos;

import com.GeladaoEstacio.Geladao.web.returnDtos.ReturnProdutoDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ItensVendidosDTO {

    private Long id;
    private ReturnProdutoDTO produto;
    private Integer quantidade;
    private VendaDTO venda;
}
