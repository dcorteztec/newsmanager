package br.com.newsmanagerApp.controller;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.newsmanagerApp.business.interfaces.IUsuarioBusiness;
import br.com.newsmanagerApp.exception.BusinessException;
import br.com.newsmanagerApp.model.UsuarioModel;
import br.com.newsmanagerApp.util.SenhaEncrypt;
import br.com.newsmanagerApp.util.UtilsMSG;
import br.com.newsmanagerApp.vo.UsuarioVO;

@Named
@SessionScoped
public class LoginController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject 
	private IUsuarioBusiness usuarioBusiness;
	@Inject 
	private UsuarioModel usuarioModel;
	private Integer contTentativa = 0;
	
	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}
	
	public UsuarioVO validarUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException, BusinessException{
		SenhaEncrypt encrypt = new SenhaEncrypt();
		
		UsuarioVO usu = usuarioBusiness.validarUsuario(usuarioModel.getLogin(), encrypt.encrypt(usuarioModel.getSenha()));
		return usu;
		
	}
	
	public String Logout(){
		 
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
 
		return "/login.xhtml?faces-redirect=true";
	}
	
	public String login() throws IOException, NoSuchAlgorithmException, BusinessException {
		if(usuarioModel.getLogin()==null || usuarioModel.getLogin().isEmpty()){
			 
			UtilsMSG.Mensagem("Favor informar o login!");
			return null;
		}
		else if(usuarioModel.getSenha()==null || usuarioModel.getSenha().isEmpty()){
 
			UtilsMSG.Mensagem("Favor informara senha!");
			return null;
		}
		else{	
           
			UsuarioVO u = validarUsuario();
 
			if(u != null){
 
				usuarioModel.setSenha(null);
                usuarioModel.setEmail(u.getEmail());
                usuarioModel.setLogin(u.getLogin());
                usuarioModel.setNome(u.getNome());
                usuarioModel.getEmail();
                FacesContext facesContext = FacesContext.getCurrentInstance();
                facesContext.getExternalContext().getSessionMap().put("usuarioAutenticado", usuarioModel);
 
 
				return "sistema/index?faces-redirect=true";
			}
			else{
				contTentativa++;
				UtilsMSG.Mensagem("Não foi possível efetuar o login com esse usuário e senha!");
				return null;
			}
		}
 
 
	}

	public Integer getContTentativa() {
		return contTentativa;
	}

	public void setContTentativa(Integer contTentativa) {
		this.contTentativa = contTentativa;
	}
 
    }
	