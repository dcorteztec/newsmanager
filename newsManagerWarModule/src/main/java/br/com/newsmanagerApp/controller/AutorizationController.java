package br.com.newsmanagerApp.controller;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.newsmanagerApp.business.interfaces.IPermissaoBusiness;
import br.com.newsmanagerApp.vo.PermissoesVO;

@Named
@RequestScoped
public class AutorizationController extends MainController{

	private static final long serialVersionUID = 1L;

	@Inject private IPermissaoBusiness permissaoBusiness;
	
	public boolean verificaPermissaoPage(String login, String page){
		boolean ret = false;
		List<PermissoesVO> list = permissaoBusiness.listPermMenu(login);
		for (PermissoesVO permissoesVO : list) {
			if(permissoesVO.getUrl()!=null&&permissoesVO.getUrl().toLowerCase().equals(page.toLowerCase())){
				ret = true;
				break;
			}
		}
		return ret;
	}
	
}
