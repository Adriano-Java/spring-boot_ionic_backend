package br.com.ans.cursomc.resources;

import br.com.ans.cursomc.domain.Categoria;
import br.com.ans.cursomc.domain.Produto;
import br.com.ans.cursomc.dto.CategoriaDTO;
import br.com.ans.cursomc.dto.ProdutoDTO;
import br.com.ans.cursomc.resources.utils.URL;
import br.com.ans.cursomc.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 */
@RestController/*Anotação que torna a classe um controlador do Spring*/
@RequestMapping(value = "/produtos")/*Anotação que mapeia a classe*/
public class ProdutoResource {
    @Autowired
    private ProdutoService service;

    /*Para que o método mapeie o serviço, use 'value' indicando o id
    * do objeto/tabela.
    * Use ResponseEntity<> no lugar do objeto que normalmente seria utilizado.
    * Esse é um objeto especial do Spring que encapsula e trata toda a transação
    * HTTP realizada pelo serviço REST.
    * O método find realiza o bind com o serviço para realizar a persistência.
    * Use @PathVariable para mapear no método o id*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Produto> find(@PathVariable Integer id){
        Produto objeto = service.find(id);
        return ResponseEntity.ok().body(objeto);
    }

    /*serviço do paginador*/
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        String nomeDecodificado = URL.decodeParam(nome);
        List<Integer> ids = URL.decodeIntList(categorias);
        Page<Produto> lista = service.search(nomeDecodificado, ids, page, linesPerPage, orderBy, direction);
        /*Page é Java 8 compliance, logo dispensa o stream() e o collect()*/
        Page<ProdutoDTO> listaDTO = lista.map(ProdutoDTO::new);
        return ResponseEntity.ok().body(listaDTO);
    }
}