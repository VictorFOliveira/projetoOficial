package com.GeladaoEstacio.Geladao.web.exception;

public class UsuarioException extends RuntimeException {

    public static final String USUARIO_EXISTENTE = "Já existe um usuário cadastrado com este nome e e-mail";
    public static final String USUARIO_NAO_ENCONTRADO = "Usuário não encontrado";
    public static final String USUARIOS_NAO_ENCONTRADOS = "Usuários não encontrados";
    public static final String USUARIO_INATIVO = "O usuário se encontra com acesso inativo";
    public static final String USUARIO_SEM_NOME = "O nome do usuário deve ser preenchido";
    public static final String USUARIO_SEM_EMAIL = "O e-mail do usuário deve ser preenchido";
    public static final String USUARIO_ADM = "O  usuário consta como ADM";
    public static final String VENDEDOR_NAO_LOCALIZADO = "O  vendedor não foi localizado";
    public static final String USUARIO_VENDEDOR = "O usuario já é vendedor";






    public UsuarioException(String message) {
        super(message);
    }
}
