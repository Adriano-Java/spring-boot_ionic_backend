package br.com.ans.cursomc.domain;

import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 11/02/2020
 */

@Entity
@RequestMapping(value = "/produtos")
public class Produto implements Serializable {

    private static final long serialVersionUID = 4527262035783040232L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;
    private Double preco;

    /*relação muitos para muitos*/
    @ManyToMany
    /*indica qual tabela será a responsável pela relação muitos pra muitos*/
    @JoinTable(/*Aqui é criada a tabela responsável pela relação entre as tabelas categoria e produto*/
            name = "PRODUTO_CATEGORIA",/*nome da tabela*/
            joinColumns = @JoinColumn(name = "produto_id"),/*definição da coluna que contém a chave estrangeira*/
            inverseJoinColumns = @JoinColumn(name = "categoria_id")/*definição da coluna que contém a chave primária*/
    )
    private List<Categoria> categorias = new ArrayList<>();

    public Produto(){}

    public Produto(Integer id, String nome, Double preco) {
        super();
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Produto)) return false;
        Produto produto = (Produto) o;
        return getId().equals(produto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}