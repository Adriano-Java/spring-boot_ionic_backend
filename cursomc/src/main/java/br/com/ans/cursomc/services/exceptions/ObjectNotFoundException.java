package br.com.ans.cursomc.services.exceptions;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 14/02/2020
 */
public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -47202518122936345L;

    public ObjectNotFoundException(String mensagem) {
        super(mensagem);
    }

    public ObjectNotFoundException(String mensagem, Throwable causa){
        super(mensagem, causa);
    }
}