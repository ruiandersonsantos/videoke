package br.com.ruianderson.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ruianderson.dao.EventoDAO;
import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.LoginOrganizador;
import br.com.ruianderson.service.LoginOrganizadorService;
import br.com.ruianderson.service.NegocioException;
import br.com.ruianderson.util.cdi.CDIServiceLocator;
import br.com.ruianderson.util.jsf.FacesUtil;

@ManagedBean
@SessionScoped
public class LoginOrganizadorBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private LoginOrganizadorService loginservice;
	
	private LoginOrganizador login;
	
	public LoginOrganizadorBean(){
		this.loginservice = CDIServiceLocator.getBean(LoginOrganizadorService.class);
	}
	
	public String logar(){
		try {
						
			this.loginservice.autenticar(login);
			// Colocando usuario logado na sessão
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", login);
			
			FacesUtil.addSuccessMessage("Organizador logado com sucesso!");
			return "/restrito/Home.xhtml?faces-redirect=true";
			
			
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
			return null;
		}
	}
	
	public String sair(){
		LoginOrganizador login = (LoginOrganizador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariologado");
		this.loginservice.sairSistema();
		
		FacesUtil.addSuccessMessage("Obrigado por usr nosso sistema!");
		
		this.limpar();
		
		return "/index.xhtml?faces-redirect=true";
	}
	
	
	
	@PostConstruct
	public void init() {
		this.limpar();
	}

	private void limpar() {
		this.login = new LoginOrganizador();	
	}

	public LoginOrganizador getLogin() {
		return login;
	}

	public void setLogin(LoginOrganizador login) {
		this.login = login;
	}
	
}
