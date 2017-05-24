package br.com.newsmanagerApp.controller;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.io.FileUtils;
import org.primefaces.context.RequestContext;

import br.com.newsmanagerApp.business.interfaces.IArquivoBusiness;
import br.com.newsmanagerApp.business.interfaces.IMessageBusiness;
import br.com.newsmanagerApp.vo.ArquivoVO;
import br.com.newsmanagerApp.vo.MessageVO;


@Named
@ViewScoped
public class MessageController extends MainController implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
    private IMessageBusiness mm;
	@Inject 
	private IArquivoBusiness arquivoBusiness; 
	
    private final List<MessageVO> messages;
    private Date lastUpdate;
    private MessageVO message;
    private String user;
    
    
    public MessageController() {
        messages = Collections.synchronizedList(new LinkedList<MessageVO>());
        lastUpdate = new Date(0);
        message = new MessageVO();
    }
 
    public Date getLastUpdate() {
        return lastUpdate;
    }
 
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
 
    public MessageVO getMessage() {
        return message;
    }
 
    public void setMessage(MessageVO message) {
        this.message = message;
    }
 
    public void sendMessage(ActionEvent evt) {
    	message.setUser(getUser());
        mm.sendMessage(message);
    }
 
    public void firstUnreadMessage(ActionEvent evt) throws IOException {
       RequestContext ctx = RequestContext.getCurrentInstance();
 
       MessageVO m = mm.getFirstAfter(lastUpdate);
 
       ctx.addCallbackParam("ok", m!=null);
       if(m==null)
           return;
 
       lastUpdate = m.getDateSent();
       DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
       ctx.addCallbackParam("user", m.getUser());
       ArquivoVO arquivo = arquivoBusiness.getByLoginUser(m.getUser());
       File file =null;
       if(arquivo!=null){
    	   file = new File(arquivo.getCaminho()); 
       }else{
    	   file = new File("/home/david/upload/avatar.png");
       }
       
      
       ctx.addCallbackParam("img", "data:image/gif;base64,"+Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(file)));
      
       ctx.addCallbackParam("dateSent", df.format(m.getDateSent()));
       ctx.addCallbackParam("text", m.getMessage());
 
    }

	public List<MessageVO> getMessages() {
		return messages;
	}

	public String getUser() {
		if(user==null){
			user = super.getLoginUsuarioLogado();
		}
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
    
}
