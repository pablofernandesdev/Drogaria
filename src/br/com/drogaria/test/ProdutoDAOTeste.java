//junit chama os testes que deseja testar na funcionalidades
package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	@Test //metodo que sera executado quando mandar rodar essa classe
	@Ignore //nao testar no momento
	public void salvar() throws SQLException{
		Produto p = new Produto();
		p.setDescricao("NOVALGINA COM 10 COMPRIMIDOS");
		p.setPreco(2.45D);
		p.setQuantidade(13L);
		
		p.setDescricao("TORSILAX COM 30 COMPRIMIDOS");
		p.setPreco(10.45D);
		p.setQuantidade(20L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(7L);
		
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
	}
	
	@Test
	
	public void listar() throws SQLException{
		ProdutoDAO dao = new ProdutoDAO();
		
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p : lista){
			System.out.println("Código: " + p.getCodigo());
			System.out.println("Descrição: " + p.getDescricao());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Código do Fabricante: " + p.getFabricante().getCodigo());
			System.out.println("Descrição do Fabricante: " + p.getFabricante().getDescricao());
			System.out.println();
			
			
		}
		
		
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(2L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}
	
	@Test
	@Ignore
	public void editar () throws SQLException{
		Produto p = new Produto();
		p.setCodigo(1L);
		p.setDescricao("DIPIRONA 15ML");
		p.setPreco(2.99);
		p.setQuantidade(15L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(8L);
		
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
		
		
	}

}
