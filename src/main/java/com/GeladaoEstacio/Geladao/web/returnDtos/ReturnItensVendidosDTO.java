package com.GeladaoEstacio.Geladao.web.returnDtos;

import com.GeladaoEstacio.Geladao.entities.Produto;
import com.GeladaoEstacio.Geladao.entities.Venda;
import com.GeladaoEstacio.Geladao.web.dtos.ProdutoDTO;
import com.GeladaoEstacio.Geladao.web.dtos.VendaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class ReturnItensVendidosDTO {

    private Long id;
    private ReturnProdutoDTO produto;
    private Integer quantidade;


}
