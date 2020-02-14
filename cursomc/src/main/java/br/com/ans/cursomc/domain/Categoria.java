package br.com.ans.cursomc.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 */
@Entity
public class Categoria implements Serializable {

    private static final long serialVersionUID = -3778514197772055155L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    /*@JsonManagedReference = Evita a referência ciclica no JSON para relacionamentos entre entidades*/
    /*mappeBy  = espelha o relacionamento entre as tabelas sendo desnecessário refazer o JoinTable para a
    * entidade do outro lado do relacionamento*/
    @JsonManagedReference
    @ManyToMany(mappedBy = "categorias")
    private List<Produto> produtos = new ArrayList<Produto>();

    public Categoria(){}

    public Categoria(Integer id, String nome) {

        super();
        this.id = id;
        this.nome = nome;

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

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categoria)) return false;
        Categoria categoria = (Categoria) o;
        return getId().equals(categoria.getId()) &&
                getNome().equals(categoria.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome());
    }

}