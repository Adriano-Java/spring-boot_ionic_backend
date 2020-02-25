package br.com.ans.cursomc.services;

import br.com.ans.cursomc.domain.Categoria;
import br.com.ans.cursomc.repositories.CategoriaRepository;
import br.com.ans.cursomc.services.exceptions.DataIntegrityException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 */
@Service/*Anotação que torna uma classe em service*/
public class CategoriaService {
    @Autowired/*Realiza a injeção (instancia o repository) do repository na classe*/
    private CategoriaRepository repository;

    public Categoria find(Integer id){
        Optional<Categoria> objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException(
                id, Categoria.class.getName() + " categoria não encontrada!")
        );
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);/*para garantir que o objeto realmente é novo, seu id deve ser nulo*/
        return repository.save(obj);
    }

    public Categoria update(Categoria obj){
        find(obj.getId());
        return repository.save(obj);
    }

    public void delete(Integer id){
        find(id);
        try{
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir uma Categoria que possui produtos!");
        }
    }
}