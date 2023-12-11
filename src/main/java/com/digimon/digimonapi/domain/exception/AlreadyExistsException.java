package com.digimon.digimonapi.domain.exception;

public class AlreadyExistsException extends RuntimeException {

    public static final long serialVersionUID = 1L;

    public AlreadyExistsException (String mensagem) {
        super(mensagem);
    }

}
