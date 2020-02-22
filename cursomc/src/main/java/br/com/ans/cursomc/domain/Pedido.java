package br.com.ans.cursomc.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 16/02/2020
 */
@Entity
public class Pedido implements Serializable {
    private static final long serialVersionUID = 7970777109609359837L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date instante;

    /*
    * cascade  permite que a execução de alguma ação na entidade de destino,
    * seja também aplicada à entidade associada.
    * mappeBy está relacionando o atributo pedido em Pagamento com esta
    * entidade*/
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pedido")
    private Pagamento pagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "endereco_de_entrega_id")
    private Endereco enderecoDeEntrega;

    /*Um pedido pode conter 'N' itens*/
    private Set<ItemPedido> itens = new HashSet<>();

    public Pedido(){}

    public Pedido(Integer id, Date instante/*, Pagamento pagamento*/, Cliente cliente, Endereco enderecoDeEntrega) {
        this.id = id;
        this.instante = instante;
//        this.pagamento = pagamento;
        this.cliente = cliente;
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Endereco getEnderecoDeEntrega() {
        return enderecoDeEntrega;
    }

    public void setEnderecoDeEntrega(Endereco enderecoDeEntrega) {
        this.enderecoDeEntrega = enderecoDeEntrega;
    }

    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pedido)) return false;
        Pedido pedido = (Pedido) o;
        return getId().equals(pedido.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}