package br.com.andrad3.despesasfinancasbackend.Exceptions;

public class InvalidEnumException extends RuntimeException{

    public InvalidEnumException(String message){
        super(message);
    }

    public InvalidEnumException(String message,Throwable cause){
        super(message,cause);
    }

}
