package br.com.newsmanagerApp.business.interfaces;

import java.util.List;

import br.com.newsmanagerApp.exception.BusinessException;
import br.com.newsmanagerApp.vo.UsuarioVO;

public interface IUsuarioBusiness {

	void persist(UsuarioVO usuarioVO) throws BusinessException;

	void merge(UsuarioVO usuarioVO) throws BusinessException;

	void remove(UsuarioVO usuarioVO) throws BusinessException;

	List<UsuarioVO> findAll() throws BusinessException;
	
	List<String> listAllLogin() throws BusinessException;

	UsuarioVO getById(Long id) throws BusinessException;
	
	UsuarioVO validarUsuario(String login,String senha) throws BusinessException;
}
