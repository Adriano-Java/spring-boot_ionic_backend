package br.com.ans.cursomc.repositories;

import br.com.ans.cursomc.domain.Estado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 15/02/2020
 */
@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
}