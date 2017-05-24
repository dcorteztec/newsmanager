package br.com.newsmanagerApp.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Table(name="TB_PERFIL")
@Entity	
public class PerfilVO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @Column(name="id_perfil") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome_perfil", nullable=false)
	private String nome;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_PERFIL_PERMISSOES", 
			   joinColumns = { @JoinColumn(name = "id_perfil") }, 
			   inverseJoinColumns = { @JoinColumn(name = "id_permissoes") })
	private List<PermissoesVO> permissoes = new ArrayList<PermissoesVO>();
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PermissoesVO>getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissoesVO> permissoes) {
		this.permissoes = permissoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	

}
