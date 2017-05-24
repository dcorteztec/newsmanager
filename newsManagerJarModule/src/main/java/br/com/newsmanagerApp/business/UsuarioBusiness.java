package br.com.newsmanagerApp.business;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.newsmanagerApp.business.interfaces.IUsuarioBusiness;
import br.com.newsmanagerApp.dao.interfaces.IUsuarioDAO;
import br.com.newsmanagerApp.exception.DaoException;
import br.com.newsmanagerApp.vo.UsuarioVO;

@ApplicationScoped
public class UsuarioBusiness implements IUsuarioBusiness{

	@Inject private IUsuarioDAO dao;
	
	@Override
	public void persist(UsuarioVO usuarioVO) {
		dao.persist(usuarioVO);
		
	}

	@Override
	public void merge(UsuarioVO usuarioVO) {
		dao.marge(usuarioVO);
		
	}

	@Override
	public void remove(UsuarioVO usuarioVO) {
		dao.removeById(usuarioVO.getId());
		
	}

	@Override
	public List<UsuarioVO> findAll() {
		
		return dao.findAll();
	}

	@Override
	public UsuarioVO getById(Long id) {
		
		return dao.getByID(id);
	}

	@Override
	public UsuarioVO validarUsuario(String login, String senha) {
		UsuarioVO vo = new UsuarioVO();
		try {
			vo= dao.validarUsuario(login,senha);
		} catch (DaoException e) {
			e.printStackTrace();
		}
		return vo;
	}

	@Override
	public List<String> listAllLogin() {
		List<String> list = new ArrayList<String>();
		try {
			list = dao.listAllLogin();
		} catch (DaoException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
