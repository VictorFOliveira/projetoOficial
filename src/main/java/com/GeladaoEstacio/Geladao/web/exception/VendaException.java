package com.GeladaoEstacio.Geladao.web.exception;

public class VendaException extends RuntimeException{

    public static final String VENDA_NAO_LOCALIZADA = "Venda não localizada";


    public VendaException(String message) {
        super(message);
    }

}
