package com.GeladaoEstacio.Geladao.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tb_produto")
public class Produto  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "produtoId")
    private Long produtoId;
    @Column(name="nomeProduto", nullable = false)
    private String nomeProduto;
    @Column(name="precoUnitario", nullable = false)
    private Double preco ;
    @Column(name="quantidade", nullable = false)
    private Integer quantidade;
    @Column(name = "validade_produto")
    private Date dataValidade;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Objects.equals(produtoId, produto.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produtoId);
    }
}
