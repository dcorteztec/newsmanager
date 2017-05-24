package br.com.newsmanagerApp.helperVO;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import br.com.newsmanagerApp.util.SenhaEncrypt;
import br.com.newsmanagerApp.vo.PerfilVO;
import br.com.newsmanagerApp.vo.UsuarioVO;

public class UsuarioHelperVO implements Serializable{

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
	
	public void setEntity(UsuarioHelperVO usuarioHelper){
		SenhaEncrypt encrypt = new SenhaEncrypt();
		this.setEmail(usuarioHelper.getEmail());
		this.setLogin(usuarioHelper.getLogin());
		this.setPerfil(usuarioHelper.getPerfil());
		this.setNome(usuarioHelper.getNome());
		try {
			this.setSenha(encrypt.encrypt(usuarioHelper.getSenha()));
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
