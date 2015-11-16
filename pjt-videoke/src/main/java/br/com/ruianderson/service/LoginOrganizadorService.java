package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ruianderson.dao.LoginOrganizadorDAO;
import br.com.ruianderson.modelo.LoginOrganizador;
import br.com.ruianderson.modelo.Organizador;

public class LoginOrganizadorService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private LoginOrganizadorDAO loginDAO;
	
	
	
	public void autenticar(LoginOrganizador login) throws NegocioException{
		Organizador organizador = null;
		organizador = loginDAO.autenticar(login.getEmail(), login.getSenha());
		
		if(organizador == null){
			throw new NegocioException("Login e/ou senha incorreto!");
		}
		login.setOrganizador(organizador);
		login.setEntradaNoSistema(Calendar.getInstance());
		
		
		loginDAO.salvarLogin(login);
		
	}



	public void sairSistema() {
		// Retirando usuario logado da sessão
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuariologado", null);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
					
	}
}
