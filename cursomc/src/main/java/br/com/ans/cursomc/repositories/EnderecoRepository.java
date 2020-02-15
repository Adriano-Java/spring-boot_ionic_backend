package br.com.ans.cursomc.repositories;

import br.com.ans.cursomc.domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * cursomc
 * Adriano Neto Da Silva
 * 09/02/2020
 */
/*@Repository torna uma classe em repository
* Uma classe repository deve ser convertida para interface
* para extender JpaRepository.
* JpaRepository necessita como paramêtros a entidade que terá
* os dados persistidos e o tipo do atributo que funciona como
* a primary key da respectiva tabela*/
@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}