package com.digimon.digimonapi.api.exceptionHandler;

import com.digimon.digimonapi.api.exceptionHandler.resposta.Resposta;
import com.digimon.digimonapi.domain.exception.NoFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoFoundException.class)
    public ResponseEntity<Object> handleNoFoundExceptional(NoFoundException ex, WebRequest request) {
        var status = HttpStatus.NOT_FOUND;

        var resposta = new Resposta();
        resposta.setStatus(status.value());
        resposta.setTitulo(ex.getMessage());
        resposta.setDataEHora(OffsetDateTime.now());

        return handleExceptionInternal(ex, resposta, new HttpHeaders(), status, request);
    }

}
