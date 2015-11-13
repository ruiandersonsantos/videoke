package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ruianderson.dao.EventoDAO;
import br.com.ruianderson.dao.OrganizadorDAO;
import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.LoginOrganizador;
import br.com.ruianderson.modelo.Organizador;
import br.com.ruianderson.util.jpa.Transactional;

public class EventoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private EventoDAO eventoDAO;
	
	
	@Inject
	private OrganizadorDAO organizadorDAO;
	private Organizador organizador;
	
	
	@Transactional
	public void fechar(Evento evento) throws NegocioException {
		this.eventoDAO.salvar(evento);
	}
	
	
	@Transactional
	public void salvar(Evento evento) throws NegocioException {
		//Verificando se existe um evento criado
		//Pegando as informações do organizador que está logado
		
		Long id = retornaIdOrganizador();
		
		if(eventoDAO.buscarEventoAtivo(id).size() > 0){
			
			throw new NegocioException("Já existe um evento aberto, não é possivel criar um novo!");
								
		}
				
		this.organizador = organizadorDAO.buscarPeloCodigo(id);
		evento.setOrganizador(organizador);
		evento.setStatus(new Long(0));
		evento.setDataInicio(Calendar.getInstance());
		this.eventoDAO.salvar(evento);
		
	}


	private Long retornaIdOrganizador() {
		LoginOrganizador loginOrganizador  = (LoginOrganizador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariologado");
		Long id = loginOrganizador.getOrganizador().getId();
		return id;
	}


	public List<Evento> buscarEventoAberto() {
		Long id = retornaIdOrganizador();
		return eventoDAO.buscarEventoAtivo(id);
	}

}
