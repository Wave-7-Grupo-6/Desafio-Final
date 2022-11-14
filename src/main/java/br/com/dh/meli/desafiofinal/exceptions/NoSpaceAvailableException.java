package br.com.dh.meli.desafiofinal.exceptions;

public class NoSpaceAvailableException extends RuntimeException{
    public NoSpaceAvailableException(String message) {
        super(message);
    }
}
