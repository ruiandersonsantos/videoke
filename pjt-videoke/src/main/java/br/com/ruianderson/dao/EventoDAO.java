package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ruianderson.modelo.Evento;




public class EventoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public void salvar(Evento evento) {
		em.merge(evento);
	}
	
	@SuppressWarnings("unchecked")
	public List<Evento> buscarTodos() {
		return em.createQuery("from Evento").getResultList();
	}
	
	
	public List<Evento> buscarEventoAtivo(Long idorganizador) {
		return em.createQuery("select e from Evento e where e.status = :status and e.organizador.id = :id",Evento.class)
				.setParameter("status", new Long(1))
				.setParameter("id", idorganizador)
				.getResultList();
	}
	
	public Evento buscarPeloCodigo(Long codigo) {
		
		return em.find(Evento.class, codigo);
	
	}

}
