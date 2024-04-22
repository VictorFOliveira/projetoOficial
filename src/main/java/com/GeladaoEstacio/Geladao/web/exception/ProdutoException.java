package com.GeladaoEstacio.Geladao.web.exception;

public class ProdutoException extends RuntimeException{

    public static final String PRODUTO_EXISTENTE = "Já existe um produto cadastrado com esse mesmo nome";
    public static final String PRODUTO_NAO_ENCONTRADO = "O produto não foi encontrado";
    public static final String PRODUTO_SEM_PRECO = "Tentativa de cadastrar um produto sem preço";
    public static final String PRODUTO_SEM_DATA_DE_VALIDADE = "Tentativa de cadastrar sem data de validade";
    public static final String PRODUTO_SEM_NOME = "Tentativa de cadastrar o produto sem nome";
    public static final String PRODUTO_SEM_QUANTIDADE_OU_ZERADA = "Tentativa de cadastrar o produto sem quantidade ou igual a 0";
    public static final String PRODUTO_COM_DATA_MENOR_QUE_DATA_ATUAL = "Tentativa de cadastrar o produto com uma data de validade do produto inferior a data atual";

    public ProdutoException(String message) {
        super(message);
    }
}

