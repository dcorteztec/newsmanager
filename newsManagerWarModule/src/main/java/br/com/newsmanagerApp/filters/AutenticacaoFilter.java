package br.com.newsmanagerApp.filters;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.newsmanagerApp.controller.AutorizationController;
import br.com.newsmanagerApp.model.UsuarioModel;

@WebFilter("/sistema/*")
public class AutenticacaoFilter implements Filter {
 
	@Inject private AutorizationController autorizationController;
	
    public AutenticacaoFilter() {
 
    }
 
	public void destroy() {
 
	}
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
 
		HttpSession httpSession 				= ((HttpServletRequest) request).getSession(); 
 
		HttpServletRequest httpServletRequest   = (HttpServletRequest) request;
 
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
 
		if(httpServletRequest.getRequestURI().indexOf("login.xhtml") <= -1){
 
			UsuarioModel usuarioModel =(UsuarioModel) httpSession.getAttribute("usuarioAutenticado");
			String[] uri = httpServletRequest.getRequestURI().split("/");
			String[] uriFinal =uri[3].toString().split("\\.");
			if(usuarioModel == null){
 
				httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/login.xhtml");
 
			}
			else{
				if(httpServletRequest.getRequestURI().equals("/newsmanagerApp/sistema/index.xhtml")||
						httpServletRequest.getRequestURI().equals("/newsmanagerApp/login.xhtml")){
					chain.doFilter(request, response);
				}
				
			else if(autorizationController.verificaPermissaoPage(usuarioModel.getLogin(),uriFinal[0].toString())){
					chain.doFilter(request, response);
				}else{
					httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/login.xhtml");
				}
			}
		}		
		else{
 
			chain.doFilter(request, response);
		}
	}
 
	public void init(FilterConfig fConfig) throws ServletException {
 
	}
 
}