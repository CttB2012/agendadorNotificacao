package com.projeto.agendadornotificacao.infrastructure.exceptions;

public class EmailException extends RuntimeException{

    public EmailException(String mensagem){
        super(mensagem);
    }

    public EmailException(String mensagem, Throwable throwable){
        super(mensagem, throwable);
    }
}