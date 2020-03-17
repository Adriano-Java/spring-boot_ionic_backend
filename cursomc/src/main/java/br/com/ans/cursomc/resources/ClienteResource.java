package br.com.ans.cursomc.resources;

import br.com.ans.cursomc.domain.Cliente;
import br.com.ans.cursomc.domain.Cliente;
import br.com.ans.cursomc.dto.ClienteDTO;
import br.com.ans.cursomc.dto.NovoClienteDTO;
import br.com.ans.cursomc.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 */
@RestController/*Anotação que torna a classe um controlador do Spring*/
@RequestMapping(value = "/clientes")/*Anotação que mapeia a classe*/
public class ClienteResource {
    @Autowired
    private ClienteService service;


    /*Para que o método mapeie o serviço, use 'value' indicando o id
    * do objeto/tabela.
    * Use ResponseEntity<> no lugar do objeto que normalmente seria utilizado.
    * Esse é um objeto especial do Spring que encapsula e trata toda a transação
    * HTTP realizada pelo serviço REST.
    * O método find realiza o bind com o serviço para realizar a persistência.
    * Use @PathVariable para mapear no método o id*/
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Cliente> find(@PathVariable Integer id){
        Cliente objeto = service.find(id);
        return ResponseEntity.ok().body(objeto);
    }

    /*Toda operação de insert deve ser POST*/
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody NovoClienteDTO objDTO){
        Cliente obj = service.fromDTO(objDTO);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /*Método para realizar o update (PUT)*/
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id){
        Cliente obj = service.fromDTO(objDTO);
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

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ClienteDTO>> findAll(){
        List<Cliente> lista = service.findAll();
        List<ClienteDTO> listaDTO = lista.stream().map(ClienteDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listaDTO);
    }

    /*serviço do paginador*/
    @RequestMapping(value = "page", method = RequestMethod.GET)
    public ResponseEntity<Page<ClienteDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        Page<Cliente> lista = service.findPage(page, linesPerPage, orderBy, direction);
        /*Page é Java 8 compliance, logo dispensa o stream() e o collect()*/
        Page<ClienteDTO> listaDTO = lista.map(ClienteDTO::new);
        return ResponseEntity.ok().body(listaDTO);
    }
}