package br.com.newsmanagerApp.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.newsmanagerApp.business.interfaces.IPermissaoBusiness;
import br.com.newsmanagerApp.model.UsuarioModel;
import br.com.newsmanagerApp.util.Constantes;
import br.com.newsmanagerApp.vo.PermissoesVO;

@Named
@SessionScoped
public class MenuController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject private IPermissaoBusiness permissaoBusiness;
	
	private List<PermissoesVO> listPerm;
	
	private String namePage;

	public List<PermissoesVO> getListPerm() {
		if(listPerm==null){
			FacesContext facesContext = FacesContext.getCurrentInstance();
			UsuarioModel model = (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
			listPerm = permissaoBusiness.listPermMenu(model.getLogin());
		}
		return listPerm;
	}

	public void setListPerm(List<PermissoesVO> listPerm) {
		this.listPerm = listPerm;
	} 
	
	public String linkMenu(String nome){
		String pageRetorno = "";
		if(nome.equals(Constantes.MENU_DIVERSOS)){
			pageRetorno= "/sistema/diversos.xhtml?faces-redirect=true";
		}
		setNamePage(nome);
		return pageRetorno;
		
	}

	public String getNamePage() {
		return namePage;
	}

	public void setNamePage(String namePage) {
		this.namePage = namePage;
	}
	
	

}
