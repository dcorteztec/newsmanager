package br.com.newsmanagerApp.view;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.newsmanagerApp.controller.MainController;

@Named
@RequestScoped
public class NavegacaoView extends MainController implements Serializable {

	private static final long serialVersionUID = 1L;

	public String menuDiversos() {
		return "/sistema/diversos.xhtml?faces-redirect=true";
	}

	public String manterUsuarios() {
		return "/sistema/manterUsuarios.xhtml?faces-redirect=true";
	}

	public String manterPerfil() {
		return "/sistema/manterPerfil.xhtml?faces-redirect=true";
	}

	public String incluirUsuario() {
		return "/sistema/form/formUsuario.xhtml?faces-redirect=true";
	}

	public String incluirPerfil() {
		return "/sistema/form/formPerfil.xhtml?faces-redirect=true";
	}

}
