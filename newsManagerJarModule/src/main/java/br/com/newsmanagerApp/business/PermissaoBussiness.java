package br.com.newsmanagerApp.business;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.newsmanagerApp.business.interfaces.IPermissaoBusiness;
import br.com.newsmanagerApp.dao.interfaces.IPermissaoDAO;
import br.com.newsmanagerApp.vo.PermissoesVO;

@ApplicationScoped
public class PermissaoBussiness implements IPermissaoBusiness{

	@Inject private IPermissaoDAO dao;
	
	@Override
	public void persist(PermissoesVO permissoesVO) {}

	@Override
	public void merge(PermissoesVO permissoesVO) {}

	@Override
	public void remove(PermissoesVO permissoesVO) {}

	@Override
	public List<PermissoesVO> findAll() {
		return dao.findAll();
	}
	@Override
	public PermissoesVO getById(Long id) {
		return null;
	}
	@Override
	public List<PermissoesVO> listPermMenu(String login) {
		return dao.listPermMenu(login);
	}

	@Override
	public List<PermissoesVO> listPermMenuByIdPerfil(Long idPerfil) {
		return dao.listPermMenuByIdPerfil(idPerfil);
	}
	
	@Override
	public List<Long> listIdPermMenuByIdPerfil(Long idPerfil) {
		return dao.listIdPermMenuByIdPerfil(idPerfil);
	}

	@Override
	public List<PermissoesVO> listAll() {
		return dao.listAll();
	}

}
