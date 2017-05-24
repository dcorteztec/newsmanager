package br.com.newsmanagerApp.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import br.com.newsmanagerApp.dao.interfaces.IUsuarioDAO;
import br.com.newsmanagerApp.exception.DaoException;
import br.com.newsmanagerApp.vo.UsuarioVO;

@ApplicationScoped
public class UsuarioDAOImpl extends GenericDAOImpl<UsuarioVO, Long> implements Serializable,  IUsuarioDAO{

	private static final long serialVersionUID = 1L;

	@Override
	public UsuarioVO validarUsuario(String login, String senha) {
		UsuarioVO vo = new UsuarioVO();
		try{	
			StringBuffer q = new StringBuffer();
	        q.append(" select u from UsuarioVO as u ");
	        q.append(" where u.login = :login AND u.senha = :senha" );
			Query query = entityManager
			    .createQuery(q.toString());
			
			query.setParameter("login", login);
			query.setParameter("senha", senha);
			vo = (UsuarioVO) query.getSingleResult();
		}catch (Exception e) {
			return null;
		}
		
		return vo;
		
	}

	@Override
	public UsuarioVO buscarCredencial(String principal) throws DaoException {
		UsuarioVO vo = new UsuarioVO();
		StringBuffer q = new StringBuffer();
        q.append(" select u from UsuarioVO as u ");
        q.append(" where u.login = :login " );
		Query query = entityManager
		    .createQuery(q.toString());
		
		query.setParameter("login", principal);
		 vo =(UsuarioVO) query.getSingleResult();
		return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> listAllLogin() throws DaoException{
		StringBuffer q = new StringBuffer();
        q.append(" select u.login from UsuarioVO as u ");
        q.append(" where u.login = :login " );
		Query query = entityManager
		    .createQuery(q.toString());
		
		return (List<String>)query.getResultList();
	}

}
