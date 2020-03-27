package br.com.ans.cursomc.dto;

import br.com.ans.cursomc.domain.Produto;

import java.io.Serializable;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 27/03/2020
 */
public class ProdutoDTO implements Serializable {
    private static final long serialVersionUID = -738103099081129908L;

    private Integer id;
    private String nome;
    private Double preco;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto obj){
        id = obj.getId();
        nome = obj.getNome();
        preco = obj.getPreco();
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
}