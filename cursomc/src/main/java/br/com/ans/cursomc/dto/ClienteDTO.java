package br.com.ans.cursomc.dto;

import br.com.ans.cursomc.domain.Cliente;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 14/03/2020
 */
public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 843627109417936345L;

    private Integer id;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Length(min = 5, max = 150, message = "O nome deve ter entre 5 a até 150 caracteres!")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório!")
    @Email(message = "E-mail inválido!")
    private String email;

    public ClienteDTO() {
    }

    public ClienteDTO(Cliente cliente) {
        id = cliente.getId();
        nome = cliente.getNome();
        email = cliente.getEmail();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}