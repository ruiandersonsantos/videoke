package br.com.ruianderson.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.ruianderson.modelo.Noticia;

public class NoticiaDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Inject
	private EntityManager em;
	
	public void salvar(Noticia noticia){
		em.merge(noticia);
	}
	
	@SuppressWarnings("unchecked")
	public List<Noticia> buscarTodas(){
		return em.createQuery("from Noticia").getResultList();
	}
}
