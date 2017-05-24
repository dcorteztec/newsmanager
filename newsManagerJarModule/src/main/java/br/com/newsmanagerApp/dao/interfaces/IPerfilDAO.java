package br.com.newsmanagerApp.dao.interfaces;

import java.util.List;

import br.com.newsmanagerApp.filtroModel.FiltroDataTableBasic;
import br.com.newsmanagerApp.vo.PerfilVO;

public interface IPerfilDAO extends IGenericDAO<PerfilVO, Long>{
	
	public List<PerfilVO> filtrados(FiltroDataTableBasic filtro);
	public int quantidadeFiltrados(FiltroDataTableBasic filtro);
}
