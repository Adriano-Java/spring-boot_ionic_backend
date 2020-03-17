package br.com.ans.cursomc.services;

import br.com.ans.cursomc.domain.Cidade;
import br.com.ans.cursomc.domain.Cliente;
import br.com.ans.cursomc.domain.Cliente;
import br.com.ans.cursomc.domain.Endereco;
import br.com.ans.cursomc.domain.enums.TipoCliente;
import br.com.ans.cursomc.dto.ClienteDTO;
import br.com.ans.cursomc.dto.NovoClienteDTO;
import br.com.ans.cursomc.repositories.CidadeRepository;
import br.com.ans.cursomc.repositories.ClienteRepository;
import br.com.ans.cursomc.repositories.EnderecoRepository;
import br.com.ans.cursomc.services.exceptions.DataIntegrityException;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private EnderecoRepository enderecoRepository;

    public Cliente find(Integer id){
        Optional<Cliente> objeto = repository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException(
                id, Cliente.class.getName() + " cliente não encontrado!")
        );
    }

    @Transactional/*para garantir que o cliente e seus endereços sejam persistidos na mesma transação*/
    public Cliente insert(Cliente obj){
        obj.setId(null);/*para garantir que o objeto realmente é novo, seu id deve ser nulo*/
        obj = repository.save(obj);
        enderecoRepository.saveAll(obj.getEnderecos());
        return obj;
    }

    public Cliente update(Cliente obj){
        Cliente clienteModificado = find(obj.getId());
        updateData(clienteModificado, obj);
        return repository.save(clienteModificado);
    }

    private void updateData(Cliente newObj, Cliente obj) {
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
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

    /**
     * Método para converter um objeto NovoClienteDTO em objeto Cliente.
     */
    public Cliente fromDTO(NovoClienteDTO objDTO){
        Cliente cliente = new Cliente(null, objDTO.getNome(), objDTO.getEmail(), objDTO.getCpfOuCnpj(), TipoCliente.toEnum(objDTO.getTipo()));
        Cidade cidade = new Cidade(objDTO.getCidadeId(), null, null);
        Endereco endereco = new Endereco(null, objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(), cliente, cidade);
        cliente.getEnderecos().add(endereco);
        cliente.getTelefones().add(objDTO.getTelefone1());

        if (objDTO.getTelefone2() != null) {
            cliente.getTelefones().add(objDTO.getTelefone2());
        }

        if (objDTO.getTelefone3() != null) {
            cliente.getTelefones().add(objDTO.getTelefone3());
        }

        return cliente;
    }
}