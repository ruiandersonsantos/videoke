package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ruianderson.modelo.Evento;
import br.com.ruianderson.modelo.FilaNoEvento;

public class FilaNoEventoDAO implements Serializable{

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public void salvar(FilaNoEvento fila) {

		em.merge(fila);
		
	}

	

	public List<FilaNoEvento> buscarFilaPorEvento(Evento evento) {
		
		return  em.createQuery("select f from FilaNoEvento f "
				+ "where f.evento.id = :evento and f.status = 0 order by f.posicao asc",FilaNoEvento.class)
				.setParameter("evento", evento.getId())
				.getResultList();
	}
}
