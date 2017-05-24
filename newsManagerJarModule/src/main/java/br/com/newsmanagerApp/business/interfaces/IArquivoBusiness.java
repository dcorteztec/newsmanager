package br.com.newsmanagerApp.business.interfaces;

import java.util.List;

import br.com.newsmanagerApp.helperVO.UsuarioHelperVO;
import br.com.newsmanagerApp.vo.ArquivoVO;

public interface IArquivoBusiness {

	void persist(ArquivoVO arquivoVO);

	void remove(ArquivoVO arquivoVO);

	List<ArquivoVO> findAll();
	
	ArquivoVO getById(Long id);
	
	ArquivoVO getByLoginUser(String login);
	
	ArquivoVO resolveAvatar(ArquivoVO arquivoVO,UsuarioHelperVO usuarioHelperVO);
}
