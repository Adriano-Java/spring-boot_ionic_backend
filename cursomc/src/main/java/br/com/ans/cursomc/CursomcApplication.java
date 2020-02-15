package br.com.ans.cursomc;

import br.com.ans.cursomc.domain.Categoria;
import br.com.ans.cursomc.domain.Cidade;
import br.com.ans.cursomc.domain.Estado;
import br.com.ans.cursomc.domain.Produto;
import br.com.ans.cursomc.repositories.CategoriaRepository;
import br.com.ans.cursomc.repositories.CidadeRepository;
import br.com.ans.cursomc.repositories.EstadoRepository;
import br.com.ans.cursomc.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * Classe responsável por iniciar o Spring.
 */
@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}
	@Autowired
    private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;

	/*Método responsável em realizar a persistência.*/
    @Override
    public void run(String... args) throws Exception {
	    Categoria cat1 = new Categoria(null, "Informática");
	    Categoria cat2 = new Categoria(null, "Escritório");

	    Produto p1 = new Produto(null, "Computador", 2000.00);
	    Produto p2 = new Produto(null, "Impressora", 800.00);
	    Produto p3 = new Produto(null, "Mouse", 80.00);

	    cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
	    cat2.getProdutos().add(p2);

	    p1.getCategorias().add(cat1);
	    p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().add(cat1);

	    categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

	    produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");

		Cidade c1 = new Cidade(null, "Uberlânida", est1);
		Cidade c2 = new Cidade(null, "SãoPaulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().add(c1);
		est2.getCidades().addAll(Arrays.asList(c2, c3));

		estadoRepository.saveAll(Arrays.asList(est1, est2));

		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
    }
}
