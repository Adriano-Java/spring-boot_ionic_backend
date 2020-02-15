package br.com.ans.cursomc.repositories;

import br.com.ans.cursomc.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 15/02/2020
 */
@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
}