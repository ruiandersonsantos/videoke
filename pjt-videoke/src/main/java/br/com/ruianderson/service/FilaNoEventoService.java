package br.com.ruianderson.service;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.inject.Inject;

import br.com.ruianderson.dao.EventoDAO;
import br.com.ruianderson.dao.FilaNoEventoDAO;
import br.com.ruianderson.dao.OrganizadorDAO;
import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.FilaNoEvento;
import br.com.ruianderson.modelo.LoginOrganizador;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.util.jpa.Transactional;

public class FilaNoEventoService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EventoDAO eventoDAO;
	
	@Inject
	private FilaNoEventoDAO filaDAO;
	
	private Evento evento;
	
	@Transactional
	public void adicionarNoEvento(FilaNoEvento fila) {
		
		pegarEventoPorOrganizador();
		
		fila.setEvento(evento);
		
		this.filaDAO.salvar(fila);
		
	}

	public void pegarEventoPorOrganizador() {
		// pegando o id do organizador
		Long id = retornaIdOrganizador();
		// pegando o evento ativo do organizador
		this.evento = eventoDAO.buscarEventoAtivo(id).get(0);
	}
	
	private Long retornaIdOrganizador() {
		LoginOrganizador loginOrganizador  = (LoginOrganizador) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuariologado");
		Long id = loginOrganizador.getOrganizador().getId();
		return id;
	}

	public List<FilaNoEvento> buscarFila() {
		// Pegando o evento ativo conforme organizador logado
		// preenche a propriedade evento
		pegarEventoPorOrganizador();
		
		return this.filaDAO.buscarFilaPorEvento(this.evento);
	}
}
