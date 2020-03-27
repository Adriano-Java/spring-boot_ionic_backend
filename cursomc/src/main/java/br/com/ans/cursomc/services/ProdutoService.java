package br.com.ans.cursomc.services;

import br.com.ans.cursomc.domain.Categoria;
import br.com.ans.cursomc.domain.Produto;
import br.com.ans.cursomc.repositories.CategoriaRepository;
import br.com.ans.cursomc.repositories.ProdutoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ProdutoService {
    @Autowired/*Realiza a injeção (instancia o repository) do repository na classe*/
    private ProdutoRepository produtoRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Integer id){
        Optional<Produto> objeto = produtoRepository.findById(id);
        return objeto.orElseThrow(() -> new ObjectNotFoundException(
                id, Produto.class.getName() + " produto não encontrado!")
        );
    }

    public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
//        return produtoRepository.search(nome, categorias, pageRequest);
        return produtoRepository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}