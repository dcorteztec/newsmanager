package br.com.newsmanagerApp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.model.UploadedFile;

import br.com.newsmanagerApp.business.interfaces.IArquivoBusiness;
import br.com.newsmanagerApp.business.interfaces.IPerfilBusiness;
import br.com.newsmanagerApp.business.interfaces.IUsuarioBusiness;
import br.com.newsmanagerApp.exception.BusinessException;
import br.com.newsmanagerApp.helperVO.UsuarioHelperVO;
import br.com.newsmanagerApp.util.UtilsMSG;
import br.com.newsmanagerApp.view.NavegacaoView;
import br.com.newsmanagerApp.vo.ArquivoVO;
import br.com.newsmanagerApp.vo.PerfilVO;
import br.com.newsmanagerApp.vo.UsuarioVO;

@Named
@ViewScoped
public class UsuarioController extends MainController implements Serializable{

	private static final long serialVersionUID = 1L;
	
    @Inject private IUsuarioBusiness usuarioBusiness;
    private UsuarioHelperVO usuarioHelperVO;
    @Inject private IPerfilBusiness perfilBusiness;
    @Inject private NavegacaoView navegacaoView;
    @Inject private IArquivoBusiness arquivoBusiness;
    private List<SelectItem> perfis;
    private UploadedFile file;
    
    
    public UsuarioHelperVO getUsuarioHelperVO() {
    	if(usuarioHelperVO==null){
    		usuarioHelperVO=new UsuarioHelperVO();
    	}
		return usuarioHelperVO;
	}
	public void setUsuarioHelperVO(UsuarioHelperVO usuarioHelperVO) {
		this.usuarioHelperVO = usuarioHelperVO;
	}
	public List<String>  listAllLogin() throws BusinessException {
		return usuarioBusiness.listAllLogin();
		
	}
	public List<SelectItem> getPerfis() {
		if(perfis==null){
			perfis = geraListaPerfis();
		}
		return perfis;
	}
	public void setPerfis(List<SelectItem> perfis) {
		this.perfis = perfis;
	}
	public String salvar() throws BusinessException{
		
		UsuarioHelperVO model = new UsuarioHelperVO(); 
		model.setEntity(usuarioHelperVO);
		UsuarioVO usuarioNovo = model.getEntity();
		ArquivoVO arquivoVO = new ArquivoVO();
		arquivoVO.setFile(getFile());
		usuarioNovo.setArquivo(arquivoBusiness.resolveAvatar(arquivoVO, model));
		usuarioBusiness.persist(usuarioNovo);
		
		UtilsMSG.MensagemAtencao("Usuário Cadastrado com Sucesso");
		RequestContext.getCurrentInstance().update("growl");
		return navegacaoView.manterUsuarios();
	}
	
	private List<SelectItem> geraListaPerfis() {
		List<PerfilVO> lista = (List<PerfilVO>) perfilBusiness.findAll();
		List<SelectItem>perfis = new ArrayList<SelectItem>();
		perfis.add(new SelectItem(null, "Selecione"));
		for (PerfilVO p : lista) {
			perfis.add(new SelectItem(p, p.getNome()));
		}
		return perfis;
	}
	
	public UploadedFile getFile() {
		return file;
	}
	public void setFile(UploadedFile file) {
		this.file = file;
	}
    
}
