package com.digimon.digimonapi.domain.exception;

public class NoFoundException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public NoFoundException(String mensagem) {
        super(mensagem);
    }

}
