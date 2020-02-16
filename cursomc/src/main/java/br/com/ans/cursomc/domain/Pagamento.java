package br.com.ans.cursomc.domain;

import br.com.ans.cursomc.domain.enums.EstadoPagamento;

import java.io.Serializable;
import java.util.Objects;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 16/02/2020
 */
public class Pagamento implements Serializable {
    private static final long serialVersionUID = -1524946843780760873L;

    private Integer id;
    private EstadoPagamento estado;

    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado;
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return estado;
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pagamento)) return false;
        Pagamento pagamento = (Pagamento) o;
        return getId().equals(pagamento.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}