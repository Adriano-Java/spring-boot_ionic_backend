package br.com.ans.cursomc.repositories;

import br.com.ans.cursomc.domain.Categoria;
import br.com.ans.cursomc.domain.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 11/02/2020
 */

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    /*
    @Query permite passar um comando em jpql para o Spring Data processar e realizar a consulta na base de dados.
    @Param do Spring Data permite pegar o valor de uma variável e passá-la como parâmetro no comando informado no @Query.
     */
//    @Query("SELECT DISTINCT obj FROM Produto obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
//    @Transactional(readOnly = true)
//    Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
    /*
    O spring-data possuí key-words e methods que permitem montar consultas como a passada no @Query acima. Utilizando
    esses key-words e methods, é dispensável o uso do @Param.
     */
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}