package br.com.ans.cursomc.resources;

import br.com.ans.cursomc.domain.Categoria;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 */
/*Anotação que torna a classe um controlador do Spring*/
@RestController
/*Anotação que mapeia a classe*/
@RequestMapping(value = "/categorias")
public class CategoriaResource {

    @RequestMapping(method = RequestMethod.GET)
    public List<Categoria> listar(){

        Categoria categoria1 = new Categoria(1, "Informática");
        Categoria categoria2 = new Categoria(2, "Escritório");

        List<Categoria> lista = new ArrayList<>();

        lista.add(categoria1);
        lista.add(categoria2);

        return lista;

    }

}