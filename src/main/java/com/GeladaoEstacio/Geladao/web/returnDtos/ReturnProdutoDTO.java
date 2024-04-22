package com.GeladaoEstacio.Geladao.web.returnDtos;

import com.GeladaoEstacio.Geladao.entities.Produto;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReturnProdutoDTO {

    private Long produtoId;
    private String nomeProduto;
    private Double preco;
    private Integer quantidade;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dataValidade;

}
