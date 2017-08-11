package br.com.fiap.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import br.com.fiap.bo.ClienteBO;
import br.com.fiap.entity.Cliente;
import br.com.fiap.exception.DBException;

@ManagedBean
public class ListaClienteBean {

	private List<Cliente> lista;
	
	private ClienteBO bo;
	
	private String nome;
	
	private int codigo;
	
	public void buscar(){
		lista = bo.buscarPorNome(nome);
	}
	
	public void remover(){
		FacesMessage msg;
		try {
			bo.remover(codigo);
			lista = bo.listar(); // atualiza a lista apos remover
			
			msg = new FacesMessage("Removido com sucesso");
		} catch (DBException e) {
			// TODO Auto-generated catch block
			msg = new FacesMessage("Falha ao remover");
			e.printStackTrace();
		}
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}
	
	public List<String> completar(String texto){
		return bo.completarPorNome(texto);
	}
	
	@PostConstruct
	private void init(){
		bo = new ClienteBO();
		lista = bo.listar();
	}

	public List<Cliente> getLista() {
		return lista;
	}

	public void setLista(List<Cliente> lista) {
		this.lista = lista;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
}
