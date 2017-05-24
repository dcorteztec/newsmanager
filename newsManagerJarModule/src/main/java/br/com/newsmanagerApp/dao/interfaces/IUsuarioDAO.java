package br.com.newsmanagerApp.dao.interfaces;

import java.util.List;

import br.com.newsmanagerApp.exception.DaoException;
import br.com.newsmanagerApp.vo.UsuarioVO;

public interface IUsuarioDAO extends IGenericDAO<UsuarioVO, Long>{

	UsuarioVO validarUsuario(String login, String senha) throws DaoException;

	UsuarioVO buscarCredencial(String principal)throws DaoException;

	List<String> listAllLogin()throws DaoException;

}
