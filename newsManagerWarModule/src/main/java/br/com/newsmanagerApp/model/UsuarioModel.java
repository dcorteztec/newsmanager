package br.com.newsmanagerApp.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.SessionScoped;

import br.com.newsmanagerApp.util.SenhaEncrypt;
import br.com.newsmanagerApp.vo.PerfilVO;
import br.com.newsmanagerApp.vo.UsuarioVO;

@SessionScoped
public class UsuarioModel implements Serializable{

	private static final long serialVersionUID = 1L;

	private String nome;
	
	private String email;
		
	private String login;
	
	private String senha;

	private PerfilVO perfil;

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
	public void setEntity(UsuarioModel usuarioModel){
		SenhaEncrypt encrypt = new SenhaEncrypt();
		this.setEmail(usuarioModel.getEmail());
		this.setLogin(usuarioModel.getLogin());
		this.setPerfil(usuarioModel.getPerfil());
		this.setNome(usuarioModel.getNome());
		try {
			this.setSenha(encrypt.encrypt(usuarioModel.getSenha()));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
	}
	
	public UsuarioVO getEntity(){
		UsuarioVO usuarioNovo = new UsuarioVO();
		usuarioNovo.setEmail(this.email);
		usuarioNovo.setLogin(this.login);
		usuarioNovo.setPerfil(this.perfil);
		usuarioNovo.setNome(this.nome);
		usuarioNovo.setSenha(this.senha);
		
		return usuarioNovo;
		
	}
	
}
