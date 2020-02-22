package br.com.ans.cursomc.services;

import br.com.ans.cursomc.domain.Pedido;
import br.com.ans.cursomc.repositories.PedidoRepository;
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
public class PedidoService {
    @Autowired/*Realiza a injeção (instancia o repository) do repository na classe*/
    private PedidoRepository repository;

    public Pedido buscar(Integer id){
        Optional<Pedido> objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException(
                id, Pedido.class.getName() + " pedido não encontrado!")
        );
    }
}