package br.com.newsmanagerApp.dao.interfaces;

import java.util.List;

import br.com.newsmanagerApp.vo.PermissoesVO;

public interface IPermissaoDAO extends IGenericDAO<PermissoesVO, Long>{

	List<PermissoesVO> listPermMenu(String login);

	List<PermissoesVO> listPermMenuByIdPerfil(Long idPerfil);

	List<Long> listIdPermMenuByIdPerfil(Long idPerfil);

	List<PermissoesVO> listAll();
	

}
