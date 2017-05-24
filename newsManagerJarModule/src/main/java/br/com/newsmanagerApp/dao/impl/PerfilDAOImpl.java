package br.com.newsmanagerApp.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import br.com.newsmanagerApp.dao.interfaces.IPerfilDAO;
import br.com.newsmanagerApp.filtroModel.FiltroDataTableBasic;
import br.com.newsmanagerApp.vo.PerfilVO;

@ApplicationScoped
public class PerfilDAOImpl extends GenericDAOImpl<PerfilVO, Long> implements Serializable, IPerfilDAO {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public List<PerfilVO> filtrados(FiltroDataTableBasic filtro) {
		List<PerfilVO> listR = new ArrayList<PerfilVO>();
		try {
			StringBuffer q = new StringBuffer();
			q.append(" select u from PerfilVO as u ");
			if (filtro.getNome() != null) {
				q.append(" where u.nome like :nome ");
			}

			Query query = entityManager.createQuery(q.toString());
			query.setFirstResult(filtro.getPrimeiroRegistro());
			query.setMaxResults(filtro.getQuantidadeRegistros());
			if (filtro.getNome() != null) {
				query.setParameter("nome", "%" + filtro.getNome() + "%");
			}
			listR = query.getResultList();
		} catch (Exception e) {
			return null;
		}

		return listR;
	}

	public int quantidadeFiltrados(FiltroDataTableBasic filtro) {
		Criteria criteria = criarCriteriaParaFiltro(filtro);

		criteria.setProjection(Projections.rowCount());

		return ((Number) criteria.uniqueResult()).intValue();
	}

	private Criteria criarCriteriaParaFiltro(FiltroDataTableBasic filtro) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(PerfilVO.class);

		if (filtro.getNome() != null) {
			criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
		}

		return criteria;
	}

}
