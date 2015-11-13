package br.com.ruianderson.service;

import java.io.Serializable;

import javax.inject.Inject;

import br.com.ruianderson.dao.ParticipanteDAO;
import br.com.ruianderson.modelo.Participante;
import br.com.ruianderson.util.jpa.Transactional;



public class ParticipanteService implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Inject
	private ParticipanteDAO fabricanteDAO;
	
	@Transactional
	public void salvar(Participante participante) throws NegocioException {
			
		this.fabricanteDAO.salvar(participante);
	}

}
