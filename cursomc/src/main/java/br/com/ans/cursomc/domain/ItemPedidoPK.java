package br.com.ans.cursomc.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 20/02/2020
 *
 * Entidade auxiliar que pemite realizar a entidade ItemPedido realizar a associação entre
 * as entidades Pedido e Produto.
 */
/*mapeamento que permite incorporar uma entidade a outras entidades,
mas que constituem uma só tabela na base de dados
*/
@Embeddable
public class ItemPedidoPK implements Serializable {
    private static final long serialVersionUID = -2860109772635326920L;

    @ManyToOne/*A entidade ItemPedido precisa conhecer o pedio e o produto, logo será relação muitos pra um*/
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedidoPK)) return false;
        ItemPedidoPK that = (ItemPedidoPK) o;
        return getPedido().equals(that.getPedido()) &&
                getProduto().equals(that.getProduto());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPedido(), getProduto());
    }
}