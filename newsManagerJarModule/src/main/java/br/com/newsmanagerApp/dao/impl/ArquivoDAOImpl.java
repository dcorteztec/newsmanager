package br.com.newsmanagerApp.dao.impl;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.newsmanagerApp.dao.interfaces.IArquivoDAO;
import br.com.newsmanagerApp.vo.ArquivoVO;

@ApplicationScoped
public class ArquivoDAOImpl extends GenericDAOImpl<ArquivoVO, Long> implements IArquivoDAO,Serializable {

	private static final long serialVersionUID = 1L;

	@Override
	public ArquivoVO getByLoginUser(String login) {
		try {
			ArquivoVO retorno = new ArquivoVO();
			StringBuffer q = new StringBuffer();
			q.append("SELECT a FROM UsuarioVO u INNER JOIN u.arquivo a WHERE u.login = :login");
			Query query = entityManager.createQuery(q.toString());
			query.setParameter("login", login);
			retorno = (ArquivoVO) query.getSingleResult();
			return retorno;
		}catch (NoResultException e) {
			return null;
		}
	}

	
	
}
