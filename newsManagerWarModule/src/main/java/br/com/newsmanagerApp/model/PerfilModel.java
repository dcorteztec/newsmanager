package br.com.newsmanagerApp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;

import br.com.newsmanagerApp.vo.PermissoesVO;

@SessionScoped
public class PerfilModel implements Serializable{

	private static final long serialVersionUID = 1L;

    private Long id;
	
	private String nome;

	private List<PermissoesVO> permissoes = new ArrayList<PermissoesVO>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PermissoesVO> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissoesVO> permissoes) {
		this.permissoes = permissoes;
	}
	
	
}
