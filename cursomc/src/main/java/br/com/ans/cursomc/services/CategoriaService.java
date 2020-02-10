package br.com.ans.cursomc.services;

import br.com.ans.cursomc.domain.Categoria;
import br.com.ans.cursomc.repositories.CategoriaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 */
/*Anotação que torna uma classe em service*/
@Service
public class CategoriaService {

    /*Realiza a injeção (instancia o repository) do repository na classe*/
    @Autowired
    private CategoriaRepository repository;

    public Categoria buscar(Integer id){

        Optional<Categoria> objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException(
                id, Categoria.class.getName() + " categoria não encontrada!")
        );

    }
}