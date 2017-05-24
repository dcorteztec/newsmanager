package br.com.newsmanagerApp.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.newsmanagerApp.business.interfaces.IPerfilBusiness;
import br.com.newsmanagerApp.business.interfaces.IPermissaoBusiness;
import br.com.newsmanagerApp.model.PerfilModel;
import br.com.newsmanagerApp.util.UtilsMSG;
import br.com.newsmanagerApp.view.NavegacaoView;
import br.com.newsmanagerApp.view.PerfilLazyView;
import br.com.newsmanagerApp.vo.PerfilVO;
import br.com.newsmanagerApp.vo.PermissoesVO;

@Named
@ViewScoped
public class PerfilController implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject private IPerfilBusiness perfilBusiness;
	@Inject private IPermissaoBusiness permissoesBusiness;
	@Inject private PerfilModel perfilModel;
	@Inject private PerfilLazyView perfillazyView;
	@Inject private NavegacaoView navegacaoView;
	
	private List<PermissoesVO> permissoesEdit = new ArrayList<PermissoesVO>();
	
	public String salvar(){
		PerfilVO perfilVO = new PerfilVO();
		if(perfilModel.getNome()!=null&&!perfilModel.getNome().isEmpty()){
			if(perfilModel.getPermissoes()!=null&&perfilModel.getPermissoes().size()!=0){
				perfilVO.setNome(perfilModel.getNome());
				List<PermissoesVO> listSet = new ArrayList<PermissoesVO>();
				listSet.addAll(perfilModel.getPermissoes());
				perfilVO.setPermissoes(listSet);			
				perfilBusiness.persist(perfilVO);
				UtilsMSG.MensagemAtencao("Perfil Cadastrado com Sucesso");
				perfillazyView.init();
				return navegacaoView.manterPerfil();
			}else{
				UtilsMSG.MensagemAtencao("É Necessário Permissões para efetuar o cadastro");
			}
			
		}else{
			UtilsMSG.MensagemAtencao("É Necessário Nome do Perfil para efetuar o cadastro");
		}
		

		return null;
	}
	
	public String excluir(){
		if(perfilModel.getId()!=null){
			PerfilVO perfil = perfilBusiness.getById(perfilModel.getId());
			perfilBusiness.remove(perfil);
			RequestContext context = RequestContext.getCurrentInstance();
			context.execute("PF('perfilDialog').hide();");
			context.update("checkboxDT");
			UtilsMSG.MensagemAtencao("Perfil Removido com Sucesso");
			perfilModel = new PerfilModel();
			perfillazyView.init();
			return navegacaoView.manterPerfil();
		}
		return null;
	}
	
	public String editar(){
		
		if(perfilModel.getNome()!=null&&!perfilModel.getNome().isEmpty()){
			if(permissoesEdit!=null&&permissoesEdit.size()!=0){
				PerfilVO perfilVO = perfilBusiness.getById(perfilModel.getId());
				perfilVO.setNome(perfilModel.getNome());
				List<PermissoesVO> listSet = new ArrayList<PermissoesVO>();
				listSet.addAll(permissoesEdit);
				perfilVO.setPermissoes(listSet);			
				perfilBusiness.merge(perfilVO);
				perfillazyView.init();
				UtilsMSG.MensagemInfo("Perfil Editado com Sucesso");
				return navegacaoView.manterPerfil();
			}else{
				UtilsMSG.MensagemAtencao("É Necessário Permissões para efetuar o cadastro");
			}
			
		}else{
			UtilsMSG.MensagemAtencao("É Necessário Nome do Perfil para efetuar o cadastro");
		}
		
		return null;
	}
	
	public PerfilModel getPerfilModel() {
		return perfilModel;
	}
	public void setPerfilModel(PerfilModel perfilModel) {
		this.perfilModel = perfilModel;
	}

	public List<PermissoesVO> getPermissoesEdit() {
		if(perfilModel.getId()!=null){
			permissoesEdit = permissoesBusiness.listPermMenuByIdPerfil(perfilModel.getId());
		}else if(permissoesEdit.size()==0){
			permissoesEdit = permissoesBusiness.listPermMenuByIdPerfil(perfilModel.getId());
		}
		return permissoesEdit;
	}

	public void setPermissoesEdit(List<PermissoesVO> permissoesEdit) {
		this.permissoesEdit = permissoesEdit;
	}
	
}
