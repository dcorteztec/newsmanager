package br.com.newsmanagerApp.vo.interfaces;

import java.io.Serializable;

public interface IEntity extends Serializable {
	public void setId(Object id);

	public Object getId();
}
