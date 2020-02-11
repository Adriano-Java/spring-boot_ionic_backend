package br.com.ans.cursomc.repositories;

import br.com.ans.cursomc.domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 11/02/2020
 */

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}