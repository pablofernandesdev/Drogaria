package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean; //bean = variaveis de telas
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
// nome pro xhml reconhecer o managed bean, usado pro xhtml achar o .java
@ViewScoped
public class FabricanteBean { // nome.java
	private Fabricante fabricante; // gravar um novo fabricante, excluir e
									// editar
	private ArrayList<Fabricante> itens;// guarda resultado de consulta
	private ArrayList<Fabricante> itensFiltrados;
	
	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItens() {
		return itens;
	}
	
	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}
	
	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	// o metodo automaticamente sera chamado antes da pagina ser desenhada
	// carregamento da consulta
	public void prepararPesquisa() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			itens = dao.listar();
			
		} catch (SQLException ex) {
			ex.printStackTrace(); // exibe a pilha de execucao pra rastrear o
									// erro
			JSFUtil.adicionarMensagemErro(ex.getMessage()); //mensagem de erro
		}
	} 
	
	public void prepararNovo (){ //metodo de preparo para salvar
		fabricante = new Fabricante();
	}

	public void novo() { // preenchida pelos valores que o usuario digita na
							// tela e salva no banco
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(fabricante);
			
			itens = dao.listar(); //atualizar a listagem
			
			
			JSFUtil.adicionarMensagemSucesso("Fabricante salvo com sucesso!");

		} catch (SQLException ex) {
			ex.printStackTrace();
			
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
	
	
	
	public void excluir(){
		
		try{
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricante);
		
			itens = dao.listar();
			
		
			JSFUtil.adicionarMensagemSucesso("Fabricante removido com sucesso!");
		}catch (SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
		
	}
	
	
	
	public void editar(){
		try{
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabricante);
		
			itens = dao.listar();
			
		
			JSFUtil.adicionarMensagemSucesso("Fabricante editado com sucesso!");
		}catch (SQLException ex){
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

}
