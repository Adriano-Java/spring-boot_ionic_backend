package br.com.ans.cursomc.services;

import br.com.ans.cursomc.domain.Cliente;
import br.com.ans.cursomc.domain.Cliente;
import br.com.ans.cursomc.dto.ClienteDTO;
import br.com.ans.cursomc.repositories.ClienteRepository;
import br.com.ans.cursomc.services.exceptions.DataIntegrityException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Cliente find(Integer id){
        Optional<Cliente> objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException(
                id, Cliente.class.getName() + " cliente não encontrado!")
        );
    }

    public Cliente insert(Cliente obj){
        obj.setId(null);/*para garantir que o objeto realmente é novo, seu id deve ser nulo*/
        return repository.save(obj);
    }

    public Cliente update(Cliente obj){
        Cliente clienteModificado = find(obj.getId());
        updateData(clienteModificado, obj);
        return repository.save(clienteModificado);
    }

    private void updateData(Cliente clienteModificado, Cliente obj) {
        clienteModificado.setNome(obj.getNome());
        clienteModificado.setEmail(obj.getEmail());
    }

    public void delete(Integer id){
        find(id);
        try{
            repository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui pedidos!");
        }
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    /*a variável direction é uma String, mas deve ser convertida para um objeto Direction*/
    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return repository.findAll(pageRequest);
    }

    /**
     * Método para converter um objeto ClienteDTO em objeto Cliente.
     */
    public Cliente fromDTO(ClienteDTO objDTO){
        return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
    }
}