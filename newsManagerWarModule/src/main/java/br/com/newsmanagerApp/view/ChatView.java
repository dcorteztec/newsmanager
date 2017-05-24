package br.com.newsmanagerApp.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.atmosphere.util.ServletContextFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

import br.com.newsmanagerApp.model.UsuarioModel;

@Named
@SessionScoped
public class ChatView implements Serializable {

	private static final long serialVersionUID = 1L;

	//private final PushContext pushContext = PushContextFactory.getDefault().getPushContext();
 
    private final EventBus eventBus = EventBusFactory.getDefault().eventBus();
 
    private String privateMessage;
     
    private String globalMessage;
     
    private String username;
     
    private boolean loggedIn;
     
    private String privateUser;
     
    private List<String> users = new ArrayList<String>();
    
    private final static String CHANNEL = "/counter";
     
    public String getPrivateUser() {
        return privateUser;
    }
 
    public void setPrivateUser(String privateUser) {
        this.privateUser = privateUser;
    }
 
    public String getGlobalMessage() {
        return globalMessage;
    }
 
    public void setGlobalMessage(String globalMessage) {
        this.globalMessage = globalMessage;
    }
 
    public String getPrivateMessage() {
        return privateMessage;
    }
 
    public void setPrivateMessage(String privateMessage) {
        this.privateMessage = privateMessage;
    }
     
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
     
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
 
    public void sendGlobal() {
        eventBus.publish(CHANNEL + "*", username + ": " + globalMessage);
         
        globalMessage = null;
    }
     
    public void sendPrivate() {
        eventBus.publish(CHANNEL + privateUser, "[PM] " + username + ": " + privateMessage);
         
        privateMessage = null;
    }
    
    private UsuarioModel usuarioLogado(){
    	FacesContext facesContext = FacesContext.getCurrentInstance();
        UsuarioModel model = (UsuarioModel) facesContext.getExternalContext().getSessionMap().get("usuarioAutenticado");
        return model;
    }
    
    @SuppressWarnings("unchecked")
	private List<String> listUsusersChat(){
    	ServletContext servletContext = ServletContextFactory.getDefault().getServletContext();
		List<String> listUsers = (List<String>)servletContext.getAttribute("listUsersChat");
    	if(listUsers==null){
    		listUsers = new ArrayList<String>();
    	}
    	return listUsers;
    	
    }
    
    public void login() {
    
        RequestContext requestContext = RequestContext.getCurrentInstance();
        UsuarioModel model = usuarioLogado();
        setUsername(model.getLogin());
        
        if(listUsusersChat().contains(model.getLogin())) {
            loggedIn = false;
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Username taken", "Try with another username."));
            requestContext.update("growl");
        }
        else {
        	users.add(model.getLogin());
        	ServletContext servletContext = ServletContextFactory.getDefault().getServletContext();
    		servletContext.setAttribute("listUsersChat", users);
            requestContext.execute("PF('subscriber').connect('/" + username + "')");
            loggedIn = true;
        }
    	
    }
     
    public void disconnect() {
        //remove user and update ui
    	UsuarioModel model = usuarioLogado();
        users.remove(model.getLogin());
        RequestContext.getCurrentInstance().update("form:users");
        //RequestContext requestContext = RequestContext.getCurrentInstance();
        //requestContext.execute("PF('subscriber').disconnect('/" + username + "')");
        
        //push leave information
        eventBus.publish(CHANNEL + "*", username + " left the channel.");
         
        //reset state
        loggedIn = false;
        username = null;
    }

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
    
    
}
