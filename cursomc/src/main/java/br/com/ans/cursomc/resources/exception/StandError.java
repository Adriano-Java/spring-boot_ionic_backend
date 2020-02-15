package br.com.ans.cursomc.resources.exception;

import java.io.Serializable;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 14/02/2020
 */
public class StandError implements Serializable {
    private static final long serialVersionUID = 3459087418530649582L;

    private Integer status;
    private String mensagem;
    private Long timeStamp;

    public StandError(Integer status, String mensagem, Long timeStamp) {
        super();
        this.status = status;
        this.mensagem = mensagem;
        this.timeStamp = timeStamp;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}