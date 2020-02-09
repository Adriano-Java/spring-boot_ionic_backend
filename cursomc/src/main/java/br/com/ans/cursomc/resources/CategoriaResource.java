package br.com.ans.cursomc.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String listar(){
        return "REST ok!";
    }

}