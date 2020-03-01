package br.com.ans.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 01/03/2020
 */
public class ValidationError extends StandError{
    private static final long serialVersionUID = 1172646570024096453L;

    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Integer status, String mensagem, Long timeStamp) {
        super(status, mensagem, timeStamp);
    }

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String mensagem) {
        errors.add(new FieldMessage(fieldName, mensagem));
    }
}