package br.com.newsmanagerApp.business.interfaces;

import java.util.List;

import br.com.newsmanagerApp.vo.PerfilVO;

public interface IPerfilBusiness {

	void persist(PerfilVO perfilVO);

	void merge(PerfilVO perfilVO);

	void remove(PerfilVO perfilVO);

	List<PerfilVO> findAll();

	PerfilVO getById(Long id);
}
