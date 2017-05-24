package br.com.newsmanagerApp.business;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.newsmanagerApp.business.interfaces.IPerfilBusiness;
import br.com.newsmanagerApp.dao.interfaces.IPerfilDAO;
import br.com.newsmanagerApp.vo.PerfilVO;

@ApplicationScoped
public class PerfilBusiness implements IPerfilBusiness{

	@Inject private IPerfilDAO dao;
	
	@Override
	public void persist(PerfilVO perfilVO) {
		dao.persist(perfilVO);
		
	}

	@Override
	public void merge(PerfilVO perfilVO) {
		dao.marge(perfilVO);
		
	}

	@Override
	public void remove(PerfilVO perfilVO) {
		dao.removeById(perfilVO.getId());
		
	}

	@Override
	public List<PerfilVO> findAll() {
		return dao.findAll();
	}

	@Override
	public PerfilVO getById(Long id) {
		return dao.getByID(id);
	}

	
}
