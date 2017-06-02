package br.com.newsmanagerApp.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Table(name="TB_PERMISSOES")
@Entity	
public class PermissoesVO  implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @Column(name="id_permissoes") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome_permissoes", nullable=false)
	private String nome;
	
	@Transient
	private boolean check;
	
	@Column(name="url_permissoes", nullable=true)
	private String url;
	
	@Column(name="menu_permissoes", nullable=true)
	private String checkMenu;
	
	@Column(name="menu_top_id", nullable=true)
	private Integer topMenuId;
	
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

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCheckMenu() {
		return checkMenu;
	}

	public void setCheckMenu(String checkMenu) {
		this.checkMenu = checkMenu;
	}

	public Integer getTopMenuId() {
		return topMenuId;
	}

	public void setTopMenuId(Integer topMenuId) {
		this.topMenuId = topMenuId;
	}
	
	
	
	
}
