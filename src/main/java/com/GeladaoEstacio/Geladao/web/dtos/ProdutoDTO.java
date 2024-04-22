    package com.GeladaoEstacio.Geladao.web.dtos;

    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    import java.util.Date;

    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public class ProdutoDTO {
        private String nomeProduto;
        private Double preco;
        private Integer quantidade;
        private Date dataValidade;

    }
