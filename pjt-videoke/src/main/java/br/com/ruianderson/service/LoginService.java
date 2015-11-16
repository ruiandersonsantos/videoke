package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.Calendar;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ruianderson.dao.LoginDAO;
import br.com.ruianderson.modelo.Login;
import br.com.ruianderson.modelo.Organizador;

public class LoginService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private LoginDAO loginDAO;
	
	
	
	public void autenticar(Login login) throws NegocioException{
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
