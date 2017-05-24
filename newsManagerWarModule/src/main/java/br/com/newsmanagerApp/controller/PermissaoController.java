package br.com.newsmanagerApp.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.newsmanagerApp.business.interfaces.IPermissaoBusiness;
import br.com.newsmanagerApp.vo.PermissoesVO;

@Named
@RequestScoped
public class PermissaoController implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private IPermissaoBusiness permissaoBusiness;
	
	private List<PermissoesVO> listPermissoes;
	
	@PostConstruct
    public void init() {
		listPermissoes = permissaoBusiness.findAll();
	}

	public List<PermissoesVO> getListPermissoes() {
		return listPermissoes;
	}

	public void setListPermissoes(List<PermissoesVO> listPermissoes) {
		this.listPermissoes = listPermissoes;
	}    

	
	
}
