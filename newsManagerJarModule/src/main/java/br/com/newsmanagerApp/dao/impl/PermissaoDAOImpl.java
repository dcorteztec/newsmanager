package br.com.newsmanagerApp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import br.com.newsmanagerApp.dao.interfaces.IPermissaoDAO;
import br.com.newsmanagerApp.vo.PermissoesVO;

@ApplicationScoped
public class PermissaoDAOImpl extends GenericDAOImpl<PermissoesVO, Long> implements Serializable,  IPermissaoDAO{

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	public List<PermissoesVO> listPermMenu(String login) {
		List<PermissoesVO> listR = new ArrayList<PermissoesVO>();
		try {
			StringBuffer q = new StringBuffer();
			q.append("SELECT PS.id_permissoes, PS.nome_permissoes FROM TB_PERMISSOES PS ");
			q.append("INNER JOIN TB_PERFIL_PERMISSOES PP ON PP.id_permissoes = PS.id_permissoes ");
			q.append("INNER JOIN TB_PERFIL PE ON PE.id_perfil = PP.id_perfil ");
			q.append("INNER JOIN TB_USUARIO US ON US.id_perfil = PP.id_perfil ");
			q.append("WHERE PS.menu_permissoes = 'S' ");
			q.append("AND US.login_usu = :login ");
			Query query = entityManager.createNativeQuery(q.toString());
			query.setParameter("login", login);
			List<Object[]> objs = query.getResultList();
			for (Object[] perm : objs) {
				PermissoesVO permissoesVO = new PermissoesVO();
				permissoesVO.setId(Long.parseLong(perm[0].toString()));
				permissoesVO.setNome(perm[1].toString());
				listR.add(permissoesVO);
			}
				
			
			
			
		} catch (Exception e) {
			return null;
		}

		return listR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PermissoesVO> listPermMenuByIdPerfil(Long idPerfil) {
		List<PermissoesVO> listR = new ArrayList<PermissoesVO>();
		try {
			StringBuffer q = new StringBuffer();
			q.append("SELECT PS.id_permissoes, PS.nome_permissoes FROM TB_PERMISSOES PS ");
			q.append("INNER JOIN TB_PERFIL_PERMISSOES PP ON PP.id_permissoes = PS.id_permissoes ");
			q.append("INNER JOIN TB_PERFIL PE ON PE.id_perfil = PP.id_perfil ");
			q.append("WHERE ");
			q.append(" PE.id_perfil = :idPerfil ");
			Query query = entityManager.createNativeQuery(q.toString());
			query.setParameter("idPerfil", idPerfil);
			List<Object[]> objs = query.getResultList();
			for (Object[] perm : objs) {
				PermissoesVO permissoesVO = new PermissoesVO();
				permissoesVO.setId(Long.parseLong(perm[0].toString()));
				permissoesVO.setNome(perm[1].toString());
				listR.add(permissoesVO);
			}
				
			
			
			
		} catch (Exception e) {
			return null;
		}

		return listR;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> listIdPermMenuByIdPerfil(Long idPerfil) {
		List<Long> objs = new ArrayList<Long>();
		try {
			StringBuffer q = new StringBuffer();
			q.append("SELECT PS.id_permissoes FROM TB_PERMISSOES PS ");
			q.append("INNER JOIN TB_PERFIL_PERMISSOES PP ON PP.id_permissoes = PS.id_permissoes ");
			q.append("INNER JOIN TB_PERFIL PE ON PE.id_perfil = PP.id_perfil ");
			q.append("WHERE ");
			q.append(" PE.id_perfil = :idPerfil ");
			Query query = entityManager.createNativeQuery(q.toString());
			query.setParameter("idPerfil", idPerfil);
			objs = query.getResultList();	
			
		} catch (Exception e) {
			return null;
		}

		return objs;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<PermissoesVO> listAll() {
		List<PermissoesVO> objs = new ArrayList<PermissoesVO>();
		try {
			StringBuffer q = new StringBuffer();
			q.append("SELECT PS FROM PermissoesVO PS ORDER BY PS.nome");
			Query query = entityManager.createQuery(q.toString());
			
			objs = (List<PermissoesVO>)query.getResultList();	
			
		} catch (Exception e) {
			return null;
		}

		return objs;
	}


}
