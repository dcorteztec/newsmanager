package br.com.newsmanagerApp.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class UtilsMSG {

	// MOSTRAR MENSAGEM
	public static void Mensagem(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage("Alerta", mensagem));
	}

	// MOSTRAR MENSAGEM
	public static void MensagemAtencao(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:" + mensagem, ""));
	}

	// MOSTRAR MENSAGEM
	public static void MensagemInfo(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}

	// MOSTRAR MENSAGEM
	public static void MensagemError(String mensagem) {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "", mensagem));
	}
}
