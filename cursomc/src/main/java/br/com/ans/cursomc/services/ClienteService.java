package br.com.ans.cursomc.services;

import br.com.ans.cursomc.domain.Cliente;
import br.com.ans.cursomc.repositories.ClienteRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 */
@Service/*Anotação que torna uma classe em service*/
public class ClienteService {
    @Autowired/*Realiza a injeção (instancia o repository) do repository na classe*/
    private ClienteRepository repository;

    public Cliente buscar(Integer id){
        Optional<Cliente> objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException(
                id, Cliente.class.getName() + " cliente não encontrado!")
        );
    }
}