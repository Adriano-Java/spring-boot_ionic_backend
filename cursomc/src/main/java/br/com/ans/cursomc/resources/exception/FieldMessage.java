package br.com.ans.cursomc.resources.exception;

import java.io.Serializable;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 01/03/2020
 *
 * Classe auxiliar para instanciar uma lista personalizada para o log de erro.
 */
public class FieldMessage implements Serializable {
    private static final long serialVersionUID = 5957671657617958892L;

    private String fieldName;
    private String message;

    public FieldMessage() {
    }

    public FieldMessage(String fieldName, String message) {
        this.fieldName = fieldName;
        this.message = message;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}