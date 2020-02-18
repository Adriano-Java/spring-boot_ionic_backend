package br.com.ans.cursomc.domain;

import br.com.ans.cursomc.domain.enums.EstadoPagamento;
import sun.java2d.pipe.SolidTextRenderer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 16/02/2020
 */
/*
@Inheritance é uma anotação para gerar as tabelas de acordo a herança existente entre a super-classe
e suas filhas. Em strategy, pode-se escolher gerar tabela única (SINGLE-TABLE) para herança de duas ou
mais classes, ou  gerar uma tabela para cada classe filha (JOINED).
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pagamento implements Serializable {
    private static final long serialVersionUID = -1524946843780760873L;

    /*
    O id do pagamento deve ser o mesmo id do pedido, por essa razão
    não se utiliza o @GeneratedValue nessa situação.
     */
    @Id
    private Integer id;

    private Integer estado;

    @OneToOne/*Relacionamento um pra um*/
    @JoinColumn(name = "pedido_id")/*para rastrear o id do pedido na base de dados*/
    @MapsId/*Permite compartilhar uma mesma chave primária entre duas entidades*/
    private Pedido pedido;

    public Pagamento() {
    }

    public Pagamento(Integer id, EstadoPagamento estado, Pedido pedido) {
        this.id = id;
        this.estado = estado.getCod();
        this.pedido = pedido;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EstadoPagamento getEstado() {
        return EstadoPagamento.toEnum(estado);
    }

    public void setEstado(EstadoPagamento estado) {
        this.estado = estado.getCod();
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