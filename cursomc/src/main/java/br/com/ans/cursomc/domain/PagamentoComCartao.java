package br.com.ans.cursomc.domain;

import br.com.ans.cursomc.domain.enums.EstadoPagamento;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 16/02/2020
 */
public class PagamentoComCartao extends Pagamento{
    private Integer numeroParcelas;

    public PagamentoComCartao() {
    }

    public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroParcelas) {
        super(id, estado, pedido);
        this.numeroParcelas = numeroParcelas;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }
}