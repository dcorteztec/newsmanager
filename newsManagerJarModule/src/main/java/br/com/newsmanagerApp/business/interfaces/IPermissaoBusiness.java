package br.com.newsmanagerApp.business.interfaces;

import java.util.List;

import br.com.newsmanagerApp.vo.PermissoesVO;

public interface IPermissaoBusiness {

	void persist(PermissoesVO permissoesVO);

	void merge(PermissoesVO permissoesVO);

	void remove(PermissoesVO permissoesVO);

	List<PermissoesVO> findAll();
	
	List<PermissoesVO> listAll();

	PermissoesVO getById(Long id);

	List<PermissoesVO> listPermMenu(String login);
	
	List<PermissoesVO> listPermMenuByIdPerfil(Long idPerfil);
	
	List<Long> listIdPermMenuByIdPerfil(Long idPerfil);
}
