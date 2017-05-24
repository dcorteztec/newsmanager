package br.com.newsmanagerApp.view;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import br.com.newsmanagerApp.business.interfaces.IPerfilBusiness;
import br.com.newsmanagerApp.business.interfaces.IPermissaoBusiness;
import br.com.newsmanagerApp.lazyModel.GenericLazyModel;
import br.com.newsmanagerApp.model.PerfilModel;
import br.com.newsmanagerApp.vo.PerfilVO;
import br.com.newsmanagerApp.vo.PermissoesVO;

@Named
@ViewScoped
public class PerfilLazyView implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private LazyDataModel<PerfilVO> lazyModel;
	
	private PerfilVO selectedPerfil;
	
	@Inject private IPerfilBusiness perfilBusiness;
    
	@Inject private PerfilModel perfilModel;
	
	@Inject private IPermissaoBusiness permissaoBusiness;
	
	@PostConstruct
    public void init() {
        lazyModel = new GenericLazyModel<PerfilVO>(perfilBusiness.findAll());
    }

	public LazyDataModel<PerfilVO> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<PerfilVO> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public PerfilVO getSelectedPerfil() {
		return selectedPerfil;
	}

	public void setSelectedPerfil(PerfilVO selectedPerfil) {
		this.selectedPerfil = selectedPerfil;
	}

	public PerfilModel getPerfilModel() {
		return perfilModel;
	}

	public void setPerfilModel(PerfilModel perfilModel) {
		this.perfilModel = perfilModel;
	}

	public void onRowSelect(SelectEvent event) {
		List<PermissoesVO> permissoes = new ArrayList<PermissoesVO>();
		List<Long> listPermPerfil  = permissaoBusiness.listIdPermMenuByIdPerfil(selectedPerfil.getId());
		selectedPerfil.setPermissoes(new ArrayList<PermissoesVO>());
			for (PermissoesVO permissoesAll : permissaoBusiness.listAll()){
				PermissoesVO perm = new PermissoesVO();
					perm.setId(permissoesAll.getId());
					perm.setNome(permissoesAll.getNome());
					if(listPermPerfil.contains(Integer.parseInt(permissoesAll.getId().toString()))){
						perm.setCheck(true);
					}else{
						perm.setCheck(false);
					}
					
				permissoes.add(perm);
		}
		perfilModel.setId(selectedPerfil.getId());
		perfilModel.setNome(selectedPerfil.getNome());	
		selectedPerfil.setPermissoes(permissoes);
	}
	
}
