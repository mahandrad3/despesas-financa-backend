package br.com.andrad3.despesasfinancasbackend.utils;

public class ReutilizarCodigo {

    public ReutilizarCodigo() {
    }

    public static String extractTokenFromAuthorizationHeader(String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
