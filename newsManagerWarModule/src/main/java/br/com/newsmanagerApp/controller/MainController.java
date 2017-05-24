package br.com.newsmanagerApp.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.newsmanagerApp.model.UsuarioModel;

@Named
@SessionScoped
public class MainController implements Serializable{

	private static final long serialVersionUID = 1L;

	private String loginUsuarioLogado;
	
	public String getLoginUsuarioLogado() {
		if(loginUsuarioLogado==null){
			loginUsuarioLogado = usuarioLogado().getLogin();
		}
		return loginUsuarioLogado;
	}

	public void setLoginUsuarioLogado(String loginUsuarioLogado) {
		this.loginUsuarioLogado = loginUsuarioLogado;
	}

	public UsuarioModel usuarioLogado(){
	  	FacesContext facesContext = FacesContext.getCurrentInstance();
	    UsuarioModel model = (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
	    return model;
	}
}
