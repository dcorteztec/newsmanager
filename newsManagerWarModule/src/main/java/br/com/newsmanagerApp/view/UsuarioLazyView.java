package br.com.newsmanagerApp.view;


import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import br.com.newsmanagerApp.business.interfaces.IUsuarioBusiness;
import br.com.newsmanagerApp.exception.BusinessException;
import br.com.newsmanagerApp.lazyModel.GenericLazyModel;
import br.com.newsmanagerApp.model.UsuarioModel;
import br.com.newsmanagerApp.vo.UsuarioVO;

@Named
@ViewScoped
public class UsuarioLazyView implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private LazyDataModel<UsuarioVO> lazyModel;
	
	private UsuarioVO selectedUsuario;
	
	@Inject private IUsuarioBusiness usuarioBusiness;
    
	@Inject private UsuarioModel usuarioModel;
	
	@PostConstruct
    public void init() throws BusinessException {
        lazyModel = new GenericLazyModel<UsuarioVO>(usuarioBusiness.findAll());
    }

	public LazyDataModel<UsuarioVO> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<UsuarioVO> lazyModel) {
		this.lazyModel = lazyModel;
	}
	
	public UsuarioVO getSelectedUsuario() {
		return selectedUsuario;
	}

	public void setSelectedUsuario(UsuarioVO selectedUsuario) {
		this.selectedUsuario = selectedUsuario;
	}

	public UsuarioModel getUsuarioModel() {
		return usuarioModel;
	}

	public void setUsuarioModel(UsuarioModel usuarioModel) {
		this.usuarioModel = usuarioModel;
	}

	public void onRowSelect(SelectEvent event) {
		
	}
	
}
