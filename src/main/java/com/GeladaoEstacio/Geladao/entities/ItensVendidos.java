package com.GeladaoEstacio.Geladao.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter@Setter@NoArgsConstructor@AllArgsConstructor@ToString
@Entity
@Table(name="tb_itens_vendidos")
public class ItensVendidos implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @Column(name = "tb_quantidade", nullable = false)
    private Integer quantidade;

    @ManyToOne
    @JoinColumn(name = "venda_id")
    @JsonBackReference
    private Venda venda;

}
