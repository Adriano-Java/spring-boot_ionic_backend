package br.com.ans.cursomc.resources;

import br.com.ans.cursomc.domain.Categoria;
import br.com.ans.cursomc.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 * Classe que controla as requisições HTTP para a entidade Categoria.
 */
@RestController/*Anotação que torna a classe um controlador do Spring*/
@RequestMapping(value = "/categorias")/*Anotação que mapeia a classe*/
public class CategoriaResource {
    @Autowired
    private CategoriaService service;

    /*Para que o método mapeie o serviço, use 'value' indicando o id
    * do objeto/tabela.
    * Use ResponseEntity<> no lugar do objeto que normalmente seria utilizado.
    * Esse é um objeto especial do Spring que encapsula e trata toda a transação
    * HTTP realizada pelo serviço REST.
    * O método find realiza o bind com o serviço para realizar a persistência.
    * Use @PathVariable para mapear no método o id*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Categoria> find(@PathVariable Integer id){
        Categoria objeto = service.find(id);
        return ResponseEntity.ok().body(objeto);
    }

    /*Toda operação de insert deve ser POST*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@RequestBody Categoria obj){
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*Método para realizar o update (PUT)*/
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id){
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    /*Método para realizar o delete (DELETE)*/
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}