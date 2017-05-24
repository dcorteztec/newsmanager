package br.com.newsmanagerApp.vo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name="TB_USUARIO")
@Entity	
public class UsuarioVO implements Serializable{

	private static final long serialVersionUID = 1L;
    
	@Id @Column(name="id_usuario") 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome_usu", nullable=false)
	private String nome;
	
	@Column(name="email_usu", nullable=false)
	private String email;
	
	@Column(name="login_usu", nullable=false)
	private String login;

	@Column(name="pwd_usu", nullable=false)
	private String senha;
	
	@ManyToOne
	@JoinColumn(name = "id_perfil", nullable = false)
	private PerfilVO perfil;
	
	@ManyToOne
	@JoinColumn(name = "id_arquivo", nullable = false)
	private ArquivoVO arquivo;
	
	@Column(name="qtd_tentativas")
	private Integer qtdTentativas;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public PerfilVO getPerfil() {
		return perfil;
	}
	public void setPerfil(PerfilVO perfil) {
		this.perfil = perfil;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Integer getQtdTentativas() {
		return qtdTentativas;
	}
	public void setQtdTentativas(Integer qtdTentativas) {
		this.qtdTentativas = qtdTentativas;
	}
	public ArquivoVO getArquivo() {
		return arquivo;
	}
	public void setArquivo(ArquivoVO arquivo) {
		this.arquivo = arquivo;
	}
	
	
}
