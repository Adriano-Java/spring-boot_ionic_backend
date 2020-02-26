package br.com.ans.cursomc.dto;

import br.com.ans.cursomc.domain.Categoria;

import java.io.Serializable;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 26/02/2020
 */
public class CategoriaDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nome;

    public CategoriaDTO() {
    }

    public CategoriaDTO(Categoria categoria) {
        id = categoria.getId();
        nome = categoria.getNome();
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
}