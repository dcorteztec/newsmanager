package br.com.newsmanagerApp.dao.interfaces;

import br.com.newsmanagerApp.vo.ArquivoVO;

public interface IArquivoDAO extends IGenericDAO<ArquivoVO, Long>{

	ArquivoVO getByLoginUser(String login);

}
