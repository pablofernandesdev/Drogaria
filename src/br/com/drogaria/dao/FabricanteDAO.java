package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {
	
	public void salvar(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante "); //append juntar coisas
		sql.append("(descricao) ");
		sql.append("VALUES (?)");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); //permite executar comandos sql
		comando.setString(1, f.getDescricao());
		
		comando.executeUpdate(); //executa o comando sql
		
	}
	
	public void excluir (Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		
		comando.executeUpdate();
		
	}
	
	public void editar (Fabricante f ) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		
		comando.executeUpdate();
		
		
	}
	// nao pode ser void porque a pesquisa retorna um resultado
	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException{ //f é o criterio da pesquisa, irá retornar um fabricante
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao  ");
		sql.append("FROM fabricante ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar(); //conexao com o banco
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString()); //substituir as ?
		comando.setLong(1, f.getCodigo());
		
		ResultSet resultado = comando.executeQuery(); //faz a consulta e retorna a consulta em uma variavel, ResultSEt guarda o resultado da consulta
		
		Fabricante retorno = null;//converter ResultSet pra Fabricante
		
		if(resultado.next()){
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
		
		
		
	}
	
	public ArrayList<Fabricante> listar () throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC"); //descrição ascendente
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while(resultado.next()){
			Fabricante f = new Fabricante ();
			f.setCodigo(resultado.getLong("codigo")); //pega a linha corrente e joga pro codigo
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f); //adiciona na coleção
		}
		
		return lista;
	}
	
	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, "%" + f.getDescricao() + "%" ); //ignorar o que esta a esquerda ou a direita
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		while(resultado.next()){
			Fabricante item = new Fabricante ();
			item.setCodigo(resultado.getLong("codigo")); //pega a linha corrente e joga pro codigo
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item); //adiciona na coleção
		}
		
		return lista;
		
	}
	
	public static void main(String[] args) {
		//***************************************************
		Fabricante f1 = new Fabricante(); // TESTAR INCLUSAO
		f1.setDescricao("DESCRICAO 1");
		
		Fabricante f2 = new Fabricante();
		f2.setDescricao("DESCRICAO 2");
		
		Fabricante f3 = new Fabricante();
		f3.setDescricao("FABRICANTE 1");
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			fdao.salvar(f1);
			fdao.salvar(f2);
			fdao.salvar(f3);
			System.out.println("Os fabricantes foram salvos com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar os fabricantes!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//*******************************************************
		/*Fabricante f1 = new Fabricante(); //TESTAR EXCLUIR
		f1.setCodigo(2L);
		
		Fabricante f2 = new Fabricante();
		f2.setCodigo(5L);
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			fdao.excluir(f1);
			fdao.excluir(f2);
			System.out.println("Fabricantes excluidos com sucesso!");
		} catch (SQLException e) {
			System.out.println("Erro ao excluir os fabricantes");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//*******************************************************
		
		Fabricante f1 = new Fabricante(); // TESTAR EDITAR
		f1.setCodigo(3L);
		f1.setDescricao("DESCRICAO 3");
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			fdao.editar(f1);
			System.out.println("Alterado com sucesso!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao alterar!");
			e.printStackTrace();
		}
		****************************************************
		Fabricante f1 = new Fabricante(); //TESTAR BUSCAR
		f1.setCodigo(3L);
		
		Fabricante f2 = new Fabricante();
		f2.setCodigo(5L);
		
		FabricanteDAO fdao = new FabricanteDAO(); //pesquisa no banco
		
		try {
			Fabricante f3= fdao.buscarPorCodigo(f1); // variaveis pra pegar a resposta, recebe o resultado da busca do f1
			Fabricante f4= fdao.buscarPorCodigo(f2);
			
			System.out.println("Resultado 1: " +f3);
			System.out.println("Resultado 2: " +f4);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Ocorreu um erro ao realizar a pesquisa!");
			e.printStackTrace();
		} 
		
		FabricanteDAO fdao = new FabricanteDAO(); //TESTAR LISTAR
		
		try {
			ArrayList<Fabricante> lista = fdao.listar();
			
			for(Fabricante f : lista){
				System.out.println("Resultado " + f);
			}
			
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar listar os fabricantes!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Fabricante f1 = new Fabricante(); //TESTAR PESQUISAR POR DESCRICAO
		f1.setDescricao("2");
		
		FabricanteDAO fdao = new FabricanteDAO ();
		
		try {
			ArrayList<Fabricante> lista = fdao.buscarPorDescricao(f1);
			for(Fabricante f : lista){
				System.out.println("Resultado " + f);
			}
		} catch (SQLException e) {
			System.out.println("Ocorreu um erro ao tentar pesquisar um fabricante!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		
	
		
		
		
		
		
		
		
	
		
		
		
		
	} 
	

}
