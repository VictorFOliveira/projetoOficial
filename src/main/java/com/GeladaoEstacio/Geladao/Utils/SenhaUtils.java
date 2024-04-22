package com.GeladaoEstacio.Geladao.Utils;

import java.security.SecureRandom;
import java.util.Base64;

public class SenhaUtils {

    public static String gerarSenhaAleatoria() {
        SecureRandom random = new SecureRandom();
        int tamanhoSenha = random.nextInt(5) + 8;
        byte[] bytes = new byte[tamanhoSenha];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);

    }
}
