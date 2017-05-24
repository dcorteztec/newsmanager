package br.com.newsmanagerApp.business;

import java.io.File;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import br.com.newsmanagerApp.business.interfaces.IArquivoBusiness;
import br.com.newsmanagerApp.dao.interfaces.IArquivoDAO;
import br.com.newsmanagerApp.helperVO.UsuarioHelperVO;
import br.com.newsmanagerApp.util.Utils;
import br.com.newsmanagerApp.vo.ArquivoVO;

@ApplicationScoped
public class ArquivoBusiness implements IArquivoBusiness{

	@Inject private IArquivoDAO dao;
	
	@Override
	public void persist(ArquivoVO arquivoVO) {
		dao.persist(arquivoVO);
		
	}

	@Override
	public void remove(ArquivoVO arquivoVO) {
		dao.removeById(arquivoVO.getId());
		
	}

	@Override
	public List<ArquivoVO> findAll() {
		
		return dao.findAll();
	}

	@Override
	public ArquivoVO getById(Long id) {
		
		return dao.getByID(id);
	}

	@Override
	public ArquivoVO getByLoginUser(String login) {
		return dao.getByLoginUser(login);
	}
	
	@Override
	public ArquivoVO resolveAvatar(ArquivoVO arquivoVO,UsuarioHelperVO usuarioHelperVO){
		Utils util = new Utils();
		byte[] foto = arquivoVO.getFile().getContents();
		String nomeArquivo = arquivoVO.getFile().getFileName();
		String arquivonew = "/home/david/upload/"+usuarioHelperVO.getLogin();
		File diretorio = new File(arquivonew);
        diretorio.mkdirs();
		String arquivo = arquivonew+"/"+nomeArquivo;
		util.criaArquivo(foto, arquivo);
		arquivoVO.setCaminho(arquivo);
		arquivoVO.setNome(nomeArquivo);
		persist(arquivoVO);
		return getById(arquivoVO.getId());
	}

}
