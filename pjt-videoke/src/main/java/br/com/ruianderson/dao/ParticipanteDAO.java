package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import br.com.ruianderson.modelo.Participante;


public class ParticipanteDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public void salvar(Participante participante) {
		em.merge(participante);
	}
	
	@SuppressWarnings("unchecked")
	public List<Participante> buscarTodos() {
		return em.createQuery("from Participante").getResultList();
	}
	
	public Participante buscarPeloCodigo(Long codigo) {
		
		return em.find(Participante.class, codigo);
	
	}

}
